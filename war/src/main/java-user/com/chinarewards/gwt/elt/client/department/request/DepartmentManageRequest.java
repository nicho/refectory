package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class DepartmentManageRequest implements
		Action<DepartmentManageResponse> {

	private String corporationId;
	private String departmentIds;

	public DepartmentManageRequest() {
	}

	public DepartmentManageRequest(String corporationId) {
		this.corporationId = corporationId;
	}
	
	public DepartmentManageRequest(String corporationId,String departmentIds) {
		this.corporationId = corporationId;
		this.departmentIds=departmentIds;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	
	
}
