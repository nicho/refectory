package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchCorpBudgetByCorpIdRequest implements
		Action<SearchCorpBudgetByCorpIdResponse> {

	private String coprid;

	public SearchCorpBudgetByCorpIdRequest() {
	}

	public SearchCorpBudgetByCorpIdRequest(String coprid) {
		this.coprid = coprid;
	}

	public String getCoprid() {
		return coprid;
	}

	public void setCoprid(String coprid) {
		this.coprid = coprid;
	}

}
