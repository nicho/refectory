/**
 * 
 */
package com.chinarewards.gwt.elt.client.department.request;

import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByIdResponse;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchDepartmentByIdRequest implements
		Action<SearchDepartmentByIdResponse> {

	private String id;

	public SearchDepartmentByIdRequest() {
	}

	public SearchDepartmentByIdRequest(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
