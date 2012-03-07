package com.chinarewards.gwt.elt.client.registerHr.model;

import java.io.Serializable;
import java.util.List;

import com.chinarewards.gwt.elt.model.user.UserRoleVo;

public class HrVo implements Serializable, Comparable<HrVo> {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String username;
	private String password;
	private String tell;
	private String email;
	private String createUserId;
	private String deptId;
	private List<UserRoleVo> UserRoleVos;
	
	public List<UserRoleVo> getUserRoleVos() {
		return UserRoleVos;
	}
	public void setUserRoleVos(List<UserRoleVo> userRoleVos) {
		UserRoleVos = userRoleVos;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	private String corpId;
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int compareTo(HrVo o) {
		return null == o ? -1 : o.getId().compareTo(id);
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + "]";
	}

}
