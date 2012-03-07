package com.chinarewards.gwt.elt.client.hrbox.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.hrbox.presenter.HrBoxPresenter.HrBoxDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class HrBoxWidget extends Composite implements	HrBoxDisplay {
	@UiField
	Panel rewardWindow;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;	
	@UiField
	InlineLabel order;
	@UiField
	InlineLabel send;
	
	@UiField
	Anchor view;	
	
	@UiField
	InlineLabel message;
	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(ViewConstants.date_format_chinese);

	private static HrBoxWidgetUiBinder uiBinder = GWT
			.create(HrBoxWidgetUiBinder.class);

	interface HrBoxWidgetUiBinder extends
			UiBinder<Widget, HrBoxWidget> {
	}

	public HrBoxWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
   
	
	@Override
	public void setOrder(String text) {
		order.setText(text);
		
	}
	@Override
	public Panel getRewardWindow() {
		return rewardWindow;
	}

	@Override
	public void setHrSend(String text) {
		send.setText(text);
		
	}

	@Override
	public HasClickHandlers getView() {
		return view;
	}
	
	@Override
	public Panel getResultpage() {
		return resultpage;
	}

	@Override
	public void setMessage(String week) {
		String time = dateFormat.format(new Date());
		
		String msg =  "今天是:" + time+" "+week;
		message.setText(msg);
	}

	@Override
	public Panel getResultPanel() {
		return resultPanel;
	}


}
