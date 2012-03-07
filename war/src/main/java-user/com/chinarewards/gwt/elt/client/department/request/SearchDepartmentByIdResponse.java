package com.chinarewards.gwt.elt.client.department.request;

import com.chinarewards.gwt.elt.client.department.model.DepartmentVo;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author yanrui
 * @since
 */
public class SearchDepartmentByIdResponse implements Result {

	private DepartmentVo departmentVo;

	public SearchDepartmentByIdResponse() {

	}

	public SearchDepartmentByIdResponse(DepartmentVo departmentVo) {
		this.departmentVo = departmentVo;
	}

	public DepartmentVo getDepartment() {
		return departmentVo;
	}

	public void setDepartment(DepartmentVo departmentVo) {
		this.departmentVo = departmentVo;
	}

}
