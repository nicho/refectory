package com.chinarewards.gwt.elt.client.enterprise.view;

import com.chinarewards.gwt.elt.client.enterprise.presenter.EnterprisePresenter.EnterpriseDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EnterpriseWidget extends Composite implements EnterpriseDisplay {

	private static EnterpriseWidgetUiBinder uiBinder = GWT
			.create(EnterpriseWidgetUiBinder.class);
	@UiField
	TextBox enterpriseName;
	@UiField
	TextBox corporation;
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
	TextBox fax;
	@UiField
	TextArea remark;
	@UiField
	Button saveButton;
	@UiField
	Hidden enterpriseId;
	@UiField
	Panel breadCrumbs;

	interface EnterpriseWidgetUiBinder extends UiBinder<Widget, EnterpriseWidget> {
	}

	public EnterpriseWidget() {
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

	public HasValue<String> getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation.setValue(corporation);
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

	public HasValue<String> getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax.setValue(fax);
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
	public String getEnterpriseId() {
		return this.enterpriseId.getValue();
	}

	@Override
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId.setValue(enterpriseId);
	}
}
