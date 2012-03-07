package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class MergeDepartmentResponse implements Result {

	String message;

	public MergeDepartmentResponse() {

	}

	public MergeDepartmentResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
