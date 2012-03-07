package com.chinarewards.gwt.elt.client.password.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author lw
 * @since 2012年2月14日 10:35:32
 */
public class PasswordResponse implements Result {

	private String message;

	public PasswordResponse() {

	}

	public PasswordResponse(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
