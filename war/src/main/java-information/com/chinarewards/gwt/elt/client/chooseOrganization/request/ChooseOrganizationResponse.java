package com.chinarewards.gwt.elt.client.chooseOrganization.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchResult;

public class ChooseOrganizationResponse implements Result {

	private OrganSearchResult result;

	public ChooseOrganizationResponse() {
	}

	public ChooseOrganizationResponse(OrganSearchResult result) {
		this.result = result;
	}

	public OrganSearchResult getResult() {
		return result;
	}

	public void setResult(OrganSearchResult result) {
		this.result = result;
	}

}
