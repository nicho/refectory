package com.chinarewards.gwt.elt.client.staffIntegral.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class StaffIntegralRequest implements Action<StaffIntegralResponse> {

	String staffId;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public StaffIntegralRequest() {
	}

	public StaffIntegralRequest(String staffId) {
		this.staffId = staffId;
	}

}
