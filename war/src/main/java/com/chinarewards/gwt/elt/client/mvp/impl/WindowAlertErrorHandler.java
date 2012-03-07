package com.chinarewards.gwt.elt.client.mvp.impl;

import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Singleton;

@Singleton
public class WindowAlertErrorHandler implements ErrorHandler {

	public void alert(String message) {
		Window.alert(message);
	}

	public void alert(String message, Throwable e) {
		// custom alert message
		Window.alert(message + "\n\n" + e.toString());
	}
}
