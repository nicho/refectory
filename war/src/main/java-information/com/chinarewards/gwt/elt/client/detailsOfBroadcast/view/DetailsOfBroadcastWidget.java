package com.chinarewards.gwt.elt.client.detailsOfBroadcast.view;

import com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter.DetailsOfBroadcastPresenter.DetailsOfBroadcastDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class DetailsOfBroadcastWidget extends Composite implements
		DetailsOfBroadcastDisplay {

	@UiField
	TextArea content;
	@UiField
	TextArea receivingObject;
	@UiField
	InlineLabel broadcastingTime;
	@UiField
	InlineLabel isAllowreplies;
	@UiField
	InlineLabel createUser;
	
	@UiField
	Button updateBtn;

	@UiField
	Panel breadCrumbs;
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	private static DetailsOfBroadcastWidgetUiBinder uiBinder = GWT
			.create(DetailsOfBroadcastWidgetUiBinder.class);

	interface DetailsOfBroadcastWidgetUiBinder extends
			UiBinder<Widget, DetailsOfBroadcastWidget> {
	}

	public DetailsOfBroadcastWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public void setContent(String text) {
		content.setText(text);
	}

	@Override
	public HasClickHandlers getUpdateBtnClickHandlers() {
		return updateBtn;
	}

	@Override
	public void setReceivingObject(String text) {
		receivingObject.setText(text);
	}

	@Override
	public void setBroadcastingTime(String text) {
		broadcastingTime.setText(text);
	}

	@Override
	public void setAllowreplies(String text) {
		isAllowreplies.setText(text);
	}

	@Override
	public void setCreateUser(String text) {
		createUser.setText(text);		
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
	public InlineLabel getDataCount() {
		return dataCount;
	}
}
