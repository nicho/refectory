package com.chinarewards.gwt.elt.client.messageLattice.view;

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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class MessageLatticeWidget extends Composite {

	@UiField
	Image photo;
	@UiField
	InlineLabel staffName;
	@UiField
	InlineLabel message;
	@UiField
	InlineLabel replyDate;
	@UiField
	Anchor replyBtn;
	@UiField
	Panel replyTextarea;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	MessageLatticeWidget widget;
	 Win win;
	 DispatchAsync dispatch;
	 SessionManager sessionManager;
	 String broadcastId;


	private static MessageListWidgetUiBinder uiBinder = GWT
			.create(MessageListWidgetUiBinder.class);

	interface MessageListWidgetUiBinder extends
			UiBinder<Widget, MessageLatticeWidget> {
	}

	public MessageLatticeWidget(final String messageId, String photo,
			String staffName, String message, String replyDate, final Win win,
			final DispatchAsync dispatch, final SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
		this.win=win;
		this.dispatch=dispatch;
		this.sessionManager=sessionManager;
		this.broadcastId=messageId;
		this.widget=this;

		
		this.staffName.setText(staffName);
		this.message.setText(message);
		this.replyDate.setText(replyDate);
		this.photo.setUrl("imageshow?imageName=" + photo);
		this.widget=this;
		replyBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				replyTextarea.clear();
				replyTextarea.add(new ReplyTextareaLatticeWidget(win, dispatch, sessionManager, messageId, widget));
			}
		});
		refWidget();
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
						MessageMyReplyShortLatticeWidget myshort=new MessageMyReplyShortLatticeWidget(win, dispatch, sessionManager, broadcastId, widget);
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
						if(tol>0)
						{
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
												new MessageReplyLatticeWidget(
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

						replyTextarea.clear();
						replyTextarea.add(grid);
						replyTextarea.add(myshort);
						}
					}

				});
	}
}
