package com.chinarewards.gwt.elt.client.message.view;

import com.chinarewards.gwt.elt.client.message.presenter.MessageListPresenter.MessageListDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class MessageListWidget extends Composite implements MessageListDisplay {

	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Button addBtn;
	@UiField
	Anchor receivedMessage;
	@UiField
	Anchor sendMessage;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static MessageListWidgetUiBinder uiBinder = GWT
			.create(MessageListWidgetUiBinder.class);

	interface MessageListWidgetUiBinder extends
			UiBinder<Widget, MessageListWidget> {
	}

	public MessageListWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);

	}

	@Override
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public Panel getResultpage() {
		return this.resultpage;
	}

	@Override
	public HasClickHandlers getAddBtn() {
		return addBtn;
	}

	@Override
	public Anchor getReceivedMessage() {
		return receivedMessage;
	}

	@Override
	public Anchor getSendMessage() {
		return sendMessage;
	}

}
