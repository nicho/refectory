package com.chinarewards.gwt.elt.client.registerHr.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;

public interface RegisterHrPresenter extends Presenter<RegisterHrPresenter.RegisterHrDisplay> {
	
	public void initRegister(String instanceId);
	public static interface RegisterHrDisplay extends Display {

		public HasClickHandlers getRegisterHrClickHandlers();
		public HasValue<String> getUsername();
		public HasValue<String> getTell();
		public HasValue<String> getName();
		public HasValue<String> getEmail();
		public HasValue<String> getPassword();
		public HasValue<String> getValidatePassword();
		

	}
}
