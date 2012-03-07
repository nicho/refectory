package com.chinarewards.gwt.elt.client.staff.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;

public interface HrRegisterPresenter extends Presenter<HrRegisterPresenter.HrRegisterDisplay> {
	
	public void initRegister(String instanceId);
	public static interface HrRegisterDisplay extends Display {

		public HasClickHandlers getHrRegisterClickHandlers();
		public HasValue<String> getUsername();
		public HasValue<String> getTell();
		public HasValue<String> getName();
		public HasValue<String> getEmail();
		public HasValue<String> getPassword();
		public HasValue<String> getValidatePassword();
		public boolean isCheckAdmin();
		public boolean isCheckStaff();
		public boolean isCheckGift();
		public boolean isCheckDeptMgr();


	}
}
