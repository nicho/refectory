package com.chinarewards.gwt.elt.client.register.view;

import com.chinarewards.gwt.elt.client.register.presenter.RegisterPresenter.RegisterDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegisterWidget extends Composite  implements RegisterDisplay {

		private static RegisterWidgetUiBinder uiBinder = GWT.create(RegisterWidgetUiBinder.class);
		@UiField
		TextBox enterpriseName;
		@UiField
		TextBox address;
		@UiField
		TextBox linkman;
		@UiField
		TextBox tell;
		@UiField
		TextBox cellphone;
		@UiField
		TextBox email;
		@UiField
		TextBox web;
		
		@UiField
		TextArea remark;
		@UiField
		Button saveButton;
		@UiField
		CheckBox see;
		

		interface RegisterWidgetUiBinder extends UiBinder<Widget, RegisterWidget> {
		}

		public RegisterWidget() {
			initWidget(uiBinder.createAndBindUi(this));
		}

		public Widget asWidget() {
			return this;
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

		
		public HasValue<String> getLinkman() {
			return linkman;
		}

		public void setLinkman(String linkman) {
			this.linkman.setValue(linkman);
		}

		public HasValue<String> getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark.setValue(remark);
		}

		public HasValue<String> getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address.setValue(address);
		}

		public HasValue<String> getTell() {
			return tell;
		}

		public void setTell(String tell) {
			this.tell.setValue(tell);
		}

		public HasValue<String> getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone.setValue(cellphone);
		}

		public HasValue<String> getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email.setValue(email);
		}

		public HasValue<String> getWeb() {
			return web;
		}

		public void setWeb(String web) {
			this.web.setValue(web);
		}

		@Override
		public boolean isCheckSee() {
			return see.getValue();
		}
	}
