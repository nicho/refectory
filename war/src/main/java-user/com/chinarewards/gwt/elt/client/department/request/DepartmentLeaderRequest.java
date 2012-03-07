package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class DepartmentLeaderRequest implements
		Action<DepartmentLeaderResponse> {

	private String leaderId;
	private String corporcationId;

	public DepartmentLeaderRequest() {
	}

	public DepartmentLeaderRequest(String leaderId, String corporcationId) {
		this.leaderId = leaderId;
		this.corporcationId = corporcationId;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getCorporcationId() {
		return corporcationId;
	}

	public void setCorporcationId(String corporcationId) {
		this.corporcationId = corporcationId;
	}

}
