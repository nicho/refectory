package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class EditCorpBudgetResponse implements Result {

	String corpBudgetId;

	public EditCorpBudgetResponse() {

	}

	public EditCorpBudgetResponse(String id) {

	}

	public String getCorpBudgetId() {
		return corpBudgetId;
	}

	public void setCorpBudgetId(String corpBudgetId) {
		this.corpBudgetId = corpBudgetId;
	}




}
