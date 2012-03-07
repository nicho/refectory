package com.chinarewards.gwt.elt.client.staff.view;


import com.chinarewards.gwt.elt.client.staff.presenter.HrRegisterPresenter.HrRegisterDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class HrRegisterWidget extends Composite implements HrRegisterDisplay {

	@UiField
	Button hrRegisterbutton;
	@UiField
	TextBox email;
	@UiField
	TextBox username;
	@UiField
	PasswordTextBox password;
	@UiField
	PasswordTextBox validatePassword;
	@UiField
	TextBox name;
	@UiField
	TextBox tell;
	@UiField
	CheckBox admin;
	@UiField
	CheckBox gift;
	@UiField
	CheckBox staff;
	@UiField
	CheckBox deptMgr;
	
	
	private static HrRegisterWidgetUiBinder uiBinder = GWT
			.create(HrRegisterWidgetUiBinder.class);

	interface HrRegisterWidgetUiBinder extends
			UiBinder<Widget, HrRegisterWidget> {
	}

	public HrRegisterWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public HasClickHandlers getHrRegisterClickHandlers() {
		return hrRegisterbutton;
	}

	@Override
	public HasValue<String> getUsername() {
		return username;
	}

	@Override
	public HasValue<String> getPassword() {
		return password;
	}

	@Override
	public HasValue<String> getTell() {
		return tell;
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public HasValue<String> getEmail() {
		return email;
	}

	@Override
	public HasValue<String> getValidatePassword() {
		return validatePassword;
	}


	@Override
	public boolean isCheckAdmin() {
		return admin.getValue();
	}

	@Override
	public boolean isCheckStaff() {
		return staff.getValue();
	}

	@Override
	public boolean isCheckGift() {
		return gift.getValue();
	}


	@Override
	public boolean isCheckDeptMgr() {
		return this.deptMgr.getValue();
	}

}
