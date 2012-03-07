package com.chinarewards.gwt.elt.client.enterprise.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class EditPeriodResponse implements Result {

	String enterpriseId;

	public EditPeriodResponse() {

	}

	public EditPeriodResponse(String id) {

	}

	public String getGiftId() {
		return enterpriseId;
	}

	public void setGiftId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}



}
