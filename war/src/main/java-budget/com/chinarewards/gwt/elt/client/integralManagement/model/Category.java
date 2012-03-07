package com.chinarewards.gwt.elt.client.integralManagement.model;

import java.io.Serializable;

public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentName;
	private String budgetpoints;
	private String hasawardedpoints;
	private String departmentId;
    private boolean isLeaf;  //是否子节点
    private String parentId;  //上级部门ID
    
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

	public Category()
	{}
	public Category(String departmentName, String budgetpoints,
			String hasawardedpoints, String departmentId,boolean isLeaf,String parentId) {
		this.departmentName = departmentName;
		this.budgetpoints = budgetpoints;
		this.hasawardedpoints = hasawardedpoints;
		this.departmentId = departmentId;
		this.isLeaf=isLeaf;
		this.parentId=parentId;
	
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBudgetpoints() {
		return budgetpoints;
	}
	public void setBudgetpoints(String budgetpoints) {
		this.budgetpoints = budgetpoints;
	}
	public String getHasawardedpoints() {
		return hasawardedpoints;
	}
	public void setHasawardedpoints(String hasawardedpoints) {
		this.hasawardedpoints = hasawardedpoints;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


}
