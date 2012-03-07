package com.chinarewards.gwt.elt.client.gloryBroadcastLattice.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GloryReplyShortLatticeWidget extends Composite {


	@UiField
	TextBox replyContent;
	@UiField
	Button replyBtn;
	@UiField
	InlineLabel replyName;
	String replyParentId;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static MyReplyShortLatticeWidgetWidgetUiBinder uiBinder = GWT
			.create(MyReplyShortLatticeWidgetWidgetUiBinder.class);

	interface MyReplyShortLatticeWidgetWidgetUiBinder extends
			UiBinder<Widget, GloryReplyShortLatticeWidget> {
	}

	public GloryReplyShortLatticeWidget(final Win win, final DispatchAsync dispatch,
			final SessionManager sessionManager,
			final String broadcastId,final int replyNumber,
			final GloryBroadcastReplyLatticeWidget widget) {
		initWidget(uiBinder.createAndBindUi(this));
		replyContent.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				if("输入你的评论…".equals(replyContent.getValue()))
				{
					replyContent.setValue("");
					replyContent.setStyleName("remark-text2");
					
				}
				
			}
		});

		replyBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (StringUtil.isEmpty(replyContent.getValue())) {
					win.alert("请填写回复内容");
					return;
				}

				dispatch.execute(new BroadcastReplyAddRequest(broadcastId,
						replyContent.getValue(), sessionManager.getSession(),replyParentId),
						new AsyncCallback<BroadcastReplyAddResponse>() {

							@Override
							public void onFailure(Throwable t) {
								win.alert(t.getMessage());
							}

							@Override
							public void onSuccess(BroadcastReplyAddResponse resp) {
								widget.refWidget();
								widget.replyNumberA.setText("回复(" + (replyNumber+1) + ")");
								widget.replyNumber=(replyNumber+1);
							}
						});

			}
		});
	}

}
