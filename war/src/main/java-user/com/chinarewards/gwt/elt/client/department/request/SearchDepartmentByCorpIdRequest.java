package com.chinarewards.gwt.elt.client.department.request;

import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByCorpIdResponse;
import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchDepartmentByCorpIdRequest implements
		Action<SearchDepartmentByCorpIdResponse> {

	private String corporationId;

	public SearchDepartmentByCorpIdRequest() {
	}

	public SearchDepartmentByCorpIdRequest(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

}
