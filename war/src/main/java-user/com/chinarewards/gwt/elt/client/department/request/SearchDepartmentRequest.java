package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.department.model.DepartmentCriteria;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentResponse;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;

/**
 * @author yanrui
 */
public class SearchDepartmentRequest implements Action<SearchDepartmentResponse> {

	private DepartmentCriteria department;
	private String corporationId;
	private UserRoleVo[] userRoles;
	private String userId;



	public SearchDepartmentRequest() {
	}

	public SearchDepartmentRequest(DepartmentCriteria department,String corporationId,UserRoleVo[] userRoles,String userId) {
		this.department = department;
		this.corporationId=corporationId;
		this.userRoles=userRoles;
		this.userId=userId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public DepartmentCriteria getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentCriteria department) {
		this.department = department;
	}

	public UserRoleVo[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoleVo[] userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
