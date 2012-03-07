package com.chinarewards.gwt.elt.client.staffAdd.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.staffAdd.presenter.StaffAddPresenter.StaffAddDisplay;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class StaffAddWidget extends Composite implements StaffAddDisplay {
	@UiField
	InlineLabel titleText;
	@UiField
	TextBox staffNo;
	@UiField
	TextBox staffName;
	@UiField
	ListBox department;
	@UiField
	TextBox jobPosition;
	@UiField
	TextBox leadership;
	@UiField
	TextBox phone;
	@UiField
	TextBox email;
	@UiField
	DateBox dob;
	@UiField
	Label dobError;
	@UiField
	RadioButton status_JOB;
	@UiField
	RadioButton status_DEPARTURE;

	@UiField
	Button addBtn;
	@UiField
	Button importBtn;

	@UiField
	Panel breadCrumbs;
	@UiField
	Image staffImage;
	@UiField
	FormPanel photoForm;
	@UiField
	FileUpload photoUpload;
	@UiField
	Button photoUploadBtn;
	@UiField
	TextBox photo;
	
	@UiField
	CheckBox admin;
	@UiField
	CheckBox gift;
	
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static StaffAddWidgetUiBinder uiBinder = GWT
			.create(StaffAddWidgetUiBinder.class);

	interface StaffAddWidgetUiBinder extends UiBinder<Widget, StaffAddWidget> {
	}

	public StaffAddWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		dob.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
	}

	@Override
	public HasClickHandlers getImportBtnClickHandlers() {
		return importBtn;
	}

	@Override
	public String getStaffNo() {
		return staffNo.getValue();
	}

	@Override
	public String getStaffName() {
		return this.staffName.getValue();
	}


	@Override
	public String getJobPosition() {
		return this.jobPosition.getValue();
	}

	@Override
	public String getLeadership() {
		return this.leadership.getValue();
	}

	@Override
	public String getPhone() {
		return this.phone.getValue();
	}

	@Override
	public String getEmail() {
		return this.email.getValue();
	}

	@Override
	public DateBox getDob() {
		return this.dob;
	}

	@Override
	public RadioButton getStatus_JOB() {
		return this.status_JOB;
	}

	@Override
	public RadioButton getStatus_DEPARTURE() {
		return this.status_DEPARTURE;
	}

	@Override
	public Image getStaffImage() {
		return this.staffImage;
	}

	@Override
	public FormPanel getPhotoForm() {
		return this.photoForm;
	}

	@Override
	public FileUpload getPhotoUpload() {
		return this.photoUpload;
	}

	@Override
	public HasClickHandlers getUploadClick() {
		return this.photoUploadBtn;
	}

	@Override
	public TextBox getPhoto() {
		return photo;
	}

	@Override
	public void setStaffNo(String text) {
		this.staffNo.setText(text);
	}

	@Override
	public void setStaffName(String text) {
		this.staffName.setText(text);
	}


	
	@Override
	public void setJobPosition(String text) {
		this.jobPosition.setText(text);
	}

	@Override
	public void setLeadership(String text) {
		this.leadership.setText(text);
	}

	@Override
	public void setPhone(String text) {
		this.phone.setText(text);
	}

	@Override
	public void setPhoto(String text) {
		this.photo.setText(text);
	}

	@Override
	public void setEmail(String text) {
		this.email.setText(text);
	}

	@Override
	public void setDob(Date date) {
		this.dob.setValue(date);
	}

	@Override
	public void setStatus(String text) {
		if (StaffStatus.JOB.toString().equals(text))
			this.status_JOB.setValue(true);
		else if (StaffStatus.DEPARTURE.toString().equals(text))
			this.status_DEPARTURE.setValue(true);
	}

	@Override
	public void setStaffImage(String text) {
		this.staffImage.setUrl("imageshow?imageName=" + text);
		staffImage.setVisible(true);
	}

	@Override
	public void setTitleText(String text) {
		titleText.setText(text);
	}

	@Override
	public ListBox getDepartment() {
		return department;
	}

	@Override
	public CheckBox getAdmin() {
		return admin;
	}

	@Override
	public CheckBox getGift() {
		return gift;
	}
}
