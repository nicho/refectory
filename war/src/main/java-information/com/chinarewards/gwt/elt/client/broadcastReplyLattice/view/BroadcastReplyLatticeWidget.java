package com.chinarewards.gwt.elt.client.broadcastReplyLattice.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListClient;
import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListCriteria;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.DateTool;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class BroadcastReplyLatticeWidget extends Composite {

	@UiField
	InlineLabel deptName;
	@UiField
	InlineLabel staffName;
	@UiField
	InlineLabel content;
	@UiField
	InlineLabel createDate;
	@UiField
	InlineLabel createDept;
	@UiField
	Anchor replyNumberA;
	@UiField
	Anchor myreply;
	@UiField
	Panel replyPanel;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	 Win win;
	 DispatchAsync dispatch;
	 SessionManager sessionManager;
	 String broadcastId;
	 BroadcastReplyLatticeWidget widget;
	 int replyNumber;
	private static BroadcastReplyLatticeWidgetUiBinder uiBinder = GWT
			.create(BroadcastReplyLatticeWidgetUiBinder.class);

	interface BroadcastReplyLatticeWidgetUiBinder extends
			UiBinder<Widget, BroadcastReplyLatticeWidget> {
	}

	public BroadcastReplyLatticeWidget(final Win win, final DispatchAsync dispatch,
			final SessionManager sessionManager, final String broadcastId, String deptName,
			String staffName, String content, String createDate,
			String createDept, final int replyNumber,boolean isAllowreplies) {
		this.win=win;
		this.dispatch=dispatch;
		this.sessionManager=sessionManager;
		this.broadcastId=broadcastId;
		this.widget=this;
		this.replyNumber=replyNumber;
		initWidget(uiBinder.createAndBindUi(this));
		if (!StringUtil.isEmpty(deptName))
			this.deptName.setText(deptName);
		if (!StringUtil.isEmpty(staffName))
			this.staffName.setText(staffName);
		if (!StringUtil.isEmpty(content))
			this.content.setText(content);
		if (!StringUtil.isEmpty(createDate))
			this.createDate.setText(createDate);
		if (!StringUtil.isEmpty(createDept))
			this.createDept.setText(createDept);

		this.replyNumberA.setText("回复(" + replyNumber + ")");
if(isAllowreplies)
{
		myreply.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				refMyreply();
			}
		});
}
else
{
	myreply.setVisible(false);
	this.widget=null;
}
		if (replyNumber != 0) {
			this.replyNumberA.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					refWidget();
				}
			});
		}
	}

	void refMyreply()
	{
		replyPanel.clear();
		replyPanel.add(new MyReplyLatticeWidget(win, dispatch,sessionManager, sessionManager.getSession().getPhoto(), broadcastId,replyNumber,widget));
	}
	
	void refWidget() {
		ReplyListCriteria cr = new ReplyListCriteria();
		cr.setBroadcastId(broadcastId);
		dispatch.execute(
				new SearchBroadcastReplyRequest(cr, sessionManager.getSession()),
				new AsyncCallback<SearchBroadcastReplyResponse>() {
					@Override
					public void onFailure(Throwable e) {
						win.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchBroadcastReplyResponse response) {
						MyReplyShortLatticeWidget myshort=new MyReplyShortLatticeWidget(win, dispatch, sessionManager, broadcastId, replyNumber, widget);
						if(widget==null)
							myshort=null;
						List<ReplyListClient> giftList = response.getResult();
						
						
						List<ReplyListClient> replyList = new ArrayList<ReplyListClient>();
						
						
						Map<String,List<ReplyListClient>> map=new HashMap<String,List<ReplyListClient>>();
						
						for (int i = 0; i < giftList.size(); i++) {
							ReplyListClient reply=giftList.get(i);
							if(StringUtil.isEmpty(reply.getParent()))
							{
								replyList.add(reply);
							}
							else
							{
								List<ReplyListClient> parentClient=map.get(reply.getParent());
								if(parentClient==null)
									parentClient=new ArrayList<ReplyListClient>();
								parentClient.add(reply);
								map.put(reply.getParent(), parentClient);
							}
						}
						
						
						
						
						
						
						int index = 0;
						int tol  = replyList.size();
						
						Grid grid = new Grid(tol, 1);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < replyList.size()) {
									ReplyListClient clint = replyList.get(index);
										grid.setWidget(
												row,
												col,
												new ReplyLatticeWidget(
														myshort,
														clint.getId(),
														clint.getReplyUserPhoto(),
														clint.getReplyUserName(),
														clint.getReplyContent(),
														DateTool.dateToStringChina2(clint
																.getReplyTime()),map)
														.asWidget());
									index++;
								} else {
									break;
									// grid.setWidget(row, col,new
									// BroadcastReplyLatticeWidget().asWidget());
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGrid");

						replyPanel.clear();
						replyPanel.add(grid);
						if(myshort!=null)
						replyPanel.add(myshort);
						
					}

				});
	}

}
