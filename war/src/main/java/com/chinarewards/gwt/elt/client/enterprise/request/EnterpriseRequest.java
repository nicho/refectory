package com.chinarewards.gwt.elt.client.enterprise.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

public class EnterpriseRequest implements Action<EnterpriseResponse> {

	private EnterpriseVo enterprisevo;
	private UserSession userSession;

	public EnterpriseRequest() {
	}

	public EnterpriseRequest(EnterpriseVo enterprisevo, UserSession userSession) {
		this.enterprisevo = enterprisevo;
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public EnterpriseVo getEnterprise() {
		return enterprisevo;
	}

	public void setEnterprise(EnterpriseVo enterprisevo) {
		this.enterprisevo = enterprisevo;
	}
}
