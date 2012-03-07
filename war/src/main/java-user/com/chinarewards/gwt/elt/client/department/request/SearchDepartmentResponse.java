package com.chinarewards.gwt.elt.client.department.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;

/**
 * @author yanrui
 */
public class SearchDepartmentResponse implements Result {

	private List<DepartmentClient> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<DepartmentClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<DepartmentClient> result) {
		this.result = result;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
