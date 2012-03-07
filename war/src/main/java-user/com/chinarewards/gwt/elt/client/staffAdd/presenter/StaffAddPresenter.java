package com.chinarewards.gwt.elt.client.staffAdd.presenter;

import java.util.Date;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface StaffAddPresenter extends
		Presenter<StaffAddPresenter.StaffAddDisplay> {

	public void initStaffUpdate(String staffId);

	public static interface StaffAddDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();

		public HasClickHandlers getImportBtnClickHandlers();

		void setBreadCrumbs(Widget breadCrumbs);

		String getStaffNo();

		String getStaffName();
		
		public ListBox getDepartment();

		String getJobPosition();

		String getLeadership();

		String getPhone();

		TextBox getPhoto();

		String getEmail();

		DateBox getDob();

		RadioButton getStatus_JOB();

		RadioButton getStatus_DEPARTURE();

		Image getStaffImage();

		FormPanel getPhotoForm();

		FileUpload getPhotoUpload();

		HasClickHandlers getUploadClick();

		void setStaffNo(String text);

		void setStaffName(String text);

		void setJobPosition(String text);

		void setLeadership(String text);

		void setPhone(String text);

		void setPhoto(String text);

		void setEmail(String text);

		void setDob(Date date);

		void setStatus(String text);

		void setStaffImage(String text);
		void setTitleText(String text);
		CheckBox getAdmin();
		CheckBox getGift();
	}
}
