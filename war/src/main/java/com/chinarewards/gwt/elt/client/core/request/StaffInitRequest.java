package com.chinarewards.gwt.elt.client.core.request;

import net.customware.gwt.dispatch.shared.Action;

public class StaffInitRequest implements Action<StaffInitResponse> {

	private String staffId;

	public StaffInitRequest() {

	}

	public StaffInitRequest(String staffId) {
		this.staffId = staffId;

	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	

}
