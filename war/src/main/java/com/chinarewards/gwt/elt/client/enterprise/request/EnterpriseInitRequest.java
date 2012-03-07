package com.chinarewards.gwt.elt.client.enterprise.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

public class EnterpriseInitRequest implements Action<EnterpriseInitResponse> {

	private UserSession userSession;

	public EnterpriseInitRequest() {
	}

	public EnterpriseInitRequest(UserSession userSession) {
		this.userSession = userSession;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
