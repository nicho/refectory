package com.chinarewards.gwt.elt.client.department.model;

import java.io.Serializable;

public class DepartmentNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentId;
	private String departmentName;

	private boolean isLeaf; // 是否子节点
//	private boolean isChecked;// 是否选中

	private String parentId; // 上级部门ID

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public DepartmentNode() {
	}

	public DepartmentNode(String departmentName, String budgetpoints,
			String hasawardedpoints, String departmentId, boolean isLeaf,
			String parentId) {
		this.departmentName = departmentName;
		this.departmentId = departmentId;
		this.isLeaf = isLeaf;
		this.parentId = parentId;

	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

//	public boolean isChecked() {
//		return isChecked;
//	}
//
//	public void setChecked(boolean isChecked) {
//		this.isChecked = isChecked;
//	}

}
