package com.chinarewards.gwt.elt.client.staffList.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class StaffGenerateUserResponse implements Result {

	private String message;

	public StaffGenerateUserResponse() {

	}

	public StaffGenerateUserResponse(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
