package com.chinarewards.gwt.elt.client.broadcastReply.view;

import com.chinarewards.gwt.elt.client.broadcastReply.presenter.BroadcastReplyPresenter.BroadcastReplyDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class BroadcastReplyWidget extends Composite implements
		BroadcastReplyDisplay {

	@UiField
	TextArea content;

	@UiField
	Button saveBtn;

	
	@UiField
	Panel breadCrumbs;


	SpecialTextArea<OrganicationClient> staffTextArea;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static BroadcastReplyWidgetUiBinder uiBinder = GWT
			.create(BroadcastReplyWidgetUiBinder.class);

	interface BroadcastReplyWidgetUiBinder extends
			UiBinder<Widget, BroadcastReplyWidget> {
	}

	public BroadcastReplyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getSaveBtnClickHandlers() {
		return saveBtn;
	}

	
	@Override
	public String getContent() {
		return content.getValue();
	}
}
