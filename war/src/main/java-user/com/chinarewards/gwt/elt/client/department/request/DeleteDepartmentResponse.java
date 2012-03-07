package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author yanrui
 */
public class DeleteDepartmentResponse implements Result {

	private String total;

	public DeleteDepartmentResponse() {
	}

	public DeleteDepartmentResponse(String total) {
		this.total = total;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
}
