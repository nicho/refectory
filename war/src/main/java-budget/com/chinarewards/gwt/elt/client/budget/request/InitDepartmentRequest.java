/**
 * 
 */
package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author lw
 * @since 2012年1月20日 19:00:40
 */
public class InitDepartmentRequest implements Action<InitDepartmentResponse> {

	
	private UserSession userSession;
	

	public InitDepartmentRequest() {
	}

	public InitDepartmentRequest(UserSession userSession) {
		
		this.userSession = userSession;
	}

	

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
