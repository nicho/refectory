package com.chinarewards.elt.model.vo;

/**
 * Show in the rewards page who satisfy the rule.
 * 
 * @author yanxin
 * 
 */
public class SatisfyStaff {
	private String id;
	private String name;
	private String deptName;
	private String staffLevelId;

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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStaffLevelId() {
		return staffLevelId;
	}

	public void setStaffLevelId(String staffLevelId) {
		this.staffLevelId = staffLevelId;
	}

}
