package com.chinarewards.gwt.elt.client.login;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.model.user.UserRoleVo;

public class LastLoginRoleRequest implements Action<LastLoginRoleResponse> {

	private String userId;
	private UserRoleVo role;

	public LastLoginRoleRequest() {

	}

	public LastLoginRoleRequest(String userId,UserRoleVo role) {
		this.userId = userId;
		this.role=role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserRoleVo getRole() {
		return role;
	}

	public void setRole(UserRoleVo role) {
		this.role = role;
	}



}
