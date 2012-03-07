package com.chinarewards.gwt.elt.client.messageLattice.view;

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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ReplyTextareaLatticeWidget extends Composite {

	@UiField
	TextArea replyContent;
	@UiField
	Button replyBtn;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static MessageListWidgetUiBinder uiBinder = GWT
			.create(MessageListWidgetUiBinder.class);

	interface MessageListWidgetUiBinder extends
			UiBinder<Widget, ReplyTextareaLatticeWidget> {
	}

	public ReplyTextareaLatticeWidget(final Win win, final DispatchAsync dispatch,
			final SessionManager sessionManager,
			final String broadcastId,
			final MessageLatticeWidget widget) {
		initWidget(uiBinder.createAndBindUi(this));
		replyBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (StringUtil.isEmpty(replyContent.getValue())) {
					win.alert("请填写回复内容");
					return;
				}

				dispatch.execute(new BroadcastReplyAddRequest(broadcastId,
						replyContent.getValue(), sessionManager.getSession()),
						new AsyncCallback<BroadcastReplyAddResponse>() {

							@Override
							public void onFailure(Throwable t) {
								win.alert(t.getMessage());
							}

							@Override
							public void onSuccess(BroadcastReplyAddResponse resp) {
								widget.refWidget();

							}
						});

			}
		});
	}

}
