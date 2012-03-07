package com.chinarewards.gwt.elt.client.staffInfo.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.staffInfo.presenter.StaffInfoPresenter.StaffInfoDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class StaffInfoWidget extends Composite implements StaffInfoDisplay {

	@UiField
	InlineLabel staffNo;
	@UiField
	InlineLabel staffName;

	@UiField
	InlineLabel departmentName;
	@UiField
	InlineLabel jobPosition;
	@UiField
	InlineLabel leadership;
	@UiField
	TextBox phone;
	@UiField
	InlineLabel email;
	@UiField
	DateBox dob;

	
	@UiField
	Button updateBtn;
	@UiField
	TextBox photo;

	@UiField
	Image staffImage;
	
	@UiField
	FormPanel photoForm;
	@UiField
	FileUpload photoUpload;
	@UiField
	Button photoUploadBtn;
	DateTimeFormat dateFormat = DateTimeFormat	.getFormat(ViewConstants.date_format);
	private static StaffInfoWidgetUiBinder uiBinder = GWT
			.create(StaffInfoWidgetUiBinder.class);

	interface StaffInfoWidgetUiBinder extends UiBinder<Widget, StaffInfoWidget> {
	}

	public StaffInfoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		dob.setFormat(new DateBox.DefaultFormat(dateFormat));
	}



	@Override
	public HasClickHandlers getupadateBtnClickHandlers() {
		return updateBtn;
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
	public void setDepartmentName(String text) {
		this.departmentName.setText(text);
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
	public HasValue<String> getPhone() {
		return phone;
	}
	@Override
	public void setEmail(String text) {
		this.email.setText(text);
	}

	@Override
	public void setDob(Date text) {
		this.dob.setValue(text);
	}
	@Override
	public HasValue<Date> getDob() {
		return dob;
	}
	@Override
	public void setStaffImage(String url) {
		this.staffImage.setUrl("imageshow?imageName=" + url);
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
	public Image getStaffImage() {
		return staffImage;
	}



	@Override
	public TextBox getPhoto() {
		return photo;
	}
}
