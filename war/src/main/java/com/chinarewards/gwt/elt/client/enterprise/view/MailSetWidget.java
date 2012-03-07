package com.chinarewards.gwt.elt.client.enterprise.view;

import com.chinarewards.gwt.elt.client.enterprise.presenter.MailSetPresenter.MailSetDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MailSetWidget extends Composite implements MailSetDisplay {

	private static MailSetWidgetUiBinder uiBinder = GWT
			.create(MailSetWidgetUiBinder.class);
	@UiField
	TextBox enterpriseName;
	
	@UiField
	TextBox email;
	@UiField
	PasswordTextBox password;
	@UiField
	TextBox smtp;
	
	@UiField
	Button saveButton;
	@UiField
	Hidden enterpriseId;
	@UiField
	Panel breadCrumbs;

	interface MailSetWidgetUiBinder extends UiBinder<Widget, MailSetWidget> {
	}

	public MailSetWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	public HasClickHandlers getSaveClickHandlers() {
		return saveButton;
	}

	public HasValue<String> getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String name) {
		this.enterpriseName.setValue(name);
	}

	

	
	public HasValue<String> getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password.setValue(password);
	}

	public HasValue<String> getSMTP() {
		return smtp;
	}

	public void setSMTP(String smtp) {
		this.smtp.setValue(smtp);
	}

	public HasValue<String> getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email.setValue(email);
	}

	

	@Override
	public String getEnterpriseId() {
		return this.enterpriseId.getValue();
	}

	@Override
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId.setValue(enterpriseId);
	}
}
