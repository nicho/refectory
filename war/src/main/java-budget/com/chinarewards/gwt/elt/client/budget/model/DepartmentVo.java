package com.chinarewards.gwt.elt.client.budget.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;

public class DepartmentVo implements Serializable {

	public DepartmentVo() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	private String departmentName;// 部门的名字
    private String id;//部门的ID
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

}
