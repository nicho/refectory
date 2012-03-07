package com.chinarewards.gwt.elt.client.staffHeavenIndex.view;

import com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter.StaffHeavenIndexPresenter.StaffHeavenIndexDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class StaffHeavenIndexWidget extends Composite implements
		StaffHeavenIndexDisplay {

	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;

	@UiField
	Anchor allInformation;
	@UiField
	Anchor staffInformation;
	@UiField
	Anchor sysInformation;
	@UiField
	Anchor themeInformation;
	@UiField
	InlineLabel topBroadcast;
	@UiField
	Anchor closeMessageBtn;
	@UiField
	InlineLabel topMessage;
	
	@UiField
	TextArea broadcastContent;
	@UiField
	CheckBox moot;
	@UiField
	Button addBroadcastBtn;
	@UiField
	Anchor quietlyInformation;
	
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static StaffHeavenIndexWidgetUiBinder uiBinder = GWT
			.create(StaffHeavenIndexWidgetUiBinder.class);

	interface StaffHeavenIndexWidgetUiBinder extends
			UiBinder<Widget, StaffHeavenIndexWidget> {
	}

	public StaffHeavenIndexWidget() {
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
	public Anchor getAllInformation() {
		return this.allInformation;
	}

	@Override
	public Anchor getStaffInformation() {
		return staffInformation;
	}

	@Override
	public Anchor getSysInformation() {
		return sysInformation;
	}

	@Override
	public Anchor getThemeInformation() {
		return themeInformation;
	}

	@Override
	public void setTopBroadcast(String text) {
		topBroadcast.setText(text);
		
	}

	@Override
	public Anchor getCloseMessageBtn() {
		return closeMessageBtn;
	}

	@Override
	public void setTopMessage(String text) {
		topMessage.setText(text);	
	}

	@Override
	public String getBroadcastContent() {
		return broadcastContent.getValue();
	}

	@Override
	public boolean getMoot() {
		return moot.getValue();
	}

	@Override
	public HasClickHandlers getAddBroadcastBtn() {
		return addBroadcastBtn;
	}

	@Override
	public void successClean() {
		broadcastContent.setValue("");
		moot.setValue(false);
	}

	@Override
	public Anchor getQuietlyInformation() {
		return quietlyInformation;
	}

}
