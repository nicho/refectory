package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author yanrui
 */
public class InitCorpBudgetByCorpIdRequest implements
		Action<InitCorpBudgetByCorpIdResponse> {
	
	private UserSession userSession;

	public InitCorpBudgetByCorpIdRequest() {
	}

	public InitCorpBudgetByCorpIdRequest(UserSession userSession) {
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	
	

}
