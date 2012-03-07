package com.chinarewards.gwt.elt.client.password.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;

public interface PasswordPresenter extends Presenter<PasswordPresenter.PasswordDisplay> {
	
	   
	    public static interface PasswordDisplay extends Display {
		public void setUsername(String text);
		public HasClickHandlers getPasswordClickHandlers();
		public HasValue<String> getUsername();
		public HasValue<String> getOldPassword();
		public HasValue<String> getNewPassword();
		public HasValue<String> getValidatePassword();
		
	}
}
