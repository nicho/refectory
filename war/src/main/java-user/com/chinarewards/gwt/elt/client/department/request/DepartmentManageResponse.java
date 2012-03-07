package com.chinarewards.gwt.elt.client.department.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;

/**
 * @author yanrui
 */
public class DepartmentManageResponse implements Result {

	private List<DepartmentNode> result;

	public DepartmentManageResponse(List<DepartmentNode> result) {
		this.result = result;
	}

	public DepartmentManageResponse() {
	}

	public List<DepartmentNode> getResult() {
		return result;
	}

	public void setResult(List<DepartmentNode> result) {
		this.result = result;
	}

}
