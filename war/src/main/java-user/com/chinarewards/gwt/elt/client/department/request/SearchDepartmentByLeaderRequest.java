package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class SearchDepartmentByLeaderRequest implements
		Action<SearchDepartmentByLeaderResponse> {

	String leaderId;

	public SearchDepartmentByLeaderRequest() {
	}

	public SearchDepartmentByLeaderRequest(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

}
