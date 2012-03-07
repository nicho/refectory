package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

/**
 * 用作auto-complete,--AC
 * 
 * @author yanxin
 * 
 */
public class StaffOrDepartmentAC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481312417059573878L;

	private String id;

	private String name;

	/**
	 * 如:陈晓明<IT部><chenxiaoming@example.com>
	 */
	private String displayName;

	private String dept;

	private String staffLevelId;

	public StaffOrDepartmentAC(String id, String name, String displayName) {
		this(id, name, displayName, "");
	}

	public StaffOrDepartmentAC(String id, String name, String displayName,
			String deptName) {
		this(id, name, displayName, "", "");
	}

	public StaffOrDepartmentAC(String id, String name, String displayName,
			String deptName, String staffLevelId) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
		this.dept = deptName;
		this.staffLevelId = staffLevelId;
	}

	public StaffOrDepartmentAC() {

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getStaffLevelId() {
		return staffLevelId;
	}

	public void setStaffLevelId(String staffLevelId) {
		this.staffLevelId = staffLevelId;
	}

}
