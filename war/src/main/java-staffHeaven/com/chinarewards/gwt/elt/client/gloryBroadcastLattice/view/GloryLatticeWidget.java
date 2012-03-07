package com.chinarewards.gwt.elt.client.gloryBroadcastLattice.view;

import java.util.List;
import java.util.Map;

import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListClient;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.util.DateTool;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class GloryLatticeWidget extends Composite {


	@UiField
	InlineLabel staffName;
	@UiField
	InlineLabel content;
	@UiField
	InlineLabel createDate;
	@UiField
	Image photo;
	@UiField
	Anchor replyBtn;
	@UiField
	Panel leafPanel;
	
	GloryReplyShortLatticeWidget myshort;
	GloryLatticeWidget mywidget;
	String replyParentId;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static ReplyLatticeWidgetWidgetUiBinder uiBinder = GWT
			.create(ReplyLatticeWidgetWidgetUiBinder.class);

	interface ReplyLatticeWidgetWidgetUiBinder extends
			UiBinder<Widget, GloryLatticeWidget> {
	}
	Map<String,List<ReplyListClient>> map;
	public GloryLatticeWidget(GloryReplyShortLatticeWidget myshort,String replyParentId, String photo,final String staffName,
			String content, String createDate,Map<String,List<ReplyListClient>> map) {
		initWidget(uiBinder.createAndBindUi(this));
		this.mywidget=this;
		this.map=map;
		if (!StringUtil.isEmpty(photo))
			this.photo.setUrl("imageshow?imageName="+photo);
		if (!StringUtil.isEmpty(staffName))
			this.staffName.setText(staffName);
		if (!StringUtil.isEmpty(content))
			this.content.setText(content);
		if (!StringUtil.isEmpty(createDate))
			this.createDate.setText(createDate);
		if(myshort!=null)
			this.myshort=myshort;
		if(replyParentId!=null)
			this.replyParentId=replyParentId;
		if(this.myshort!=null)
		replyBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mywidget.myshort.replyContent.setFocus(true);
				mywidget.myshort.replyContent.setValue("");
				mywidget.myshort.replyParentId=mywidget.replyParentId;
				mywidget.myshort.replyName.getElement().getParentElement().getParentElement().setClassName("remarkdiv");
				mywidget.myshort.replyName.setText(staffName);
			}
		});
		
		if(this.map!=null)
		{
			List<ReplyListClient> replyList=map.get(replyParentId);
			if(replyList!=null && replyList.size()>0)
			{
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
										new GloryLatticeWidget(
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

				leafPanel.clear();
				leafPanel.add(grid);

			}
		}
		
	}

}
