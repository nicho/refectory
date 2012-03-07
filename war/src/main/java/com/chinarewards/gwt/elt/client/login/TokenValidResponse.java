package com.chinarewards.gwt.elt.client.login;


import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.model.user.UserRoleVo;

public class TokenValidResponse implements Result {
	private String token;
	String loginName;
	String corporationId;
	String departmentId;
	UserRoleVo[] userRoles;
	String staffId;
    UserRoleVo lastLoginRole;
    String photo;
    String corporationName;
    
    
	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public UserRoleVo getLastLoginRole() {
		return lastLoginRole;
	}

	public void setLastLoginRole(UserRoleVo lastLoginRole) {
		this.lastLoginRole = lastLoginRole;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public UserRoleVo[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoleVo[] userRoles) {
		this.userRoles = userRoles;
	}

	public TokenValidResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
