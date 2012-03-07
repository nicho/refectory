package com.chinarewards.gwt.elt.client.box.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.box.presenter.UserBoxPresenter.UserBoxDisplay;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class UserBoxWidget extends Composite implements	UserBoxDisplay {

	
		
	@UiField
	InlineLabel news;
	@UiField
	InlineLabel send;
	
	@UiField
	Anchor view;	
	@UiField
	Anchor operate;
	@UiField
	InlineLabel message;
	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(ViewConstants.date_format_chinese);

	private static UserBoxWidgetUiBinder uiBinder = GWT
			.create(UserBoxWidgetUiBinder.class);

	interface UserBoxWidgetUiBinder extends
			UiBinder<Widget, UserBoxWidget> {
	}

	public UserBoxWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
   
	
	
	
	@Override
	public void setUserBoxNominate(String text) {
		news.setText(text);
		
	}

	@Override
	public void setUserSend(String text) {
		send.setText(text);
		
	}

	@Override
	public HasClickHandlers getView() {
		return view;
	}
	
	@Override
	public HasClickHandlers getOperate() {
		return operate;
	}

	@Override
	public void setMessage(String week) {
		String time = dateFormat.format(new Date());
		
		String msg =  "今天是:" + time+" "+week;
		message.setText(msg);
	}

}
