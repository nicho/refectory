package com.chinarewards.gwt.elt.client.department.request;

import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentResponse;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class DeleteDepartmentRequest implements
		Action<DeleteDepartmentResponse> {

	private String departmentId;
	private String userId;

	public DeleteDepartmentRequest() {
	}

	public DeleteDepartmentRequest(String departmentId) {
		this.departmentId = departmentId;
	}

	public DeleteDepartmentRequest(String departmentId, String userId) {
		this.departmentId = departmentId;
		this.userId = userId;

	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
