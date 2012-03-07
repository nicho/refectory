package com.chinarewards.gwt.elt.client.mvp;


public interface ErrorHandler {

	void alert(String message);

	void alert(String message, Throwable e);

}
