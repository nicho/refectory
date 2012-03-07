package com.chinarewards.gwt.elt.client.staffInfo.presenter;

import java.util.Date;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;

public interface StaffInfoPresenter extends
		Presenter<StaffInfoPresenter.StaffInfoDisplay> {

		public static interface StaffInfoDisplay extends Display {
		public HasClickHandlers getupadateBtnClickHandlers();
	
		void setStaffNo(String text);
		void setStaffName(String text);
		void setDepartmentName(String text);
		void setJobPosition(String text);
		void setLeadership(String text);
		void setPhone(String text);
		void setEmail(String text);
		public void setDob(Date text);
		public HasValue<Date> getDob() ;

		void setStaffImage(String url);
		public HasValue<String> getPhone();
		FormPanel getPhotoForm();

		FileUpload getPhotoUpload();

		HasClickHandlers getUploadClick();
		Image getStaffImage();
		TextBox getPhoto();
	}
}
