/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author lw
 */
public class OrderBoxRequest implements Action<OrderBoxResponse> {

	private String status;
	private UserSession userSession;

	public OrderBoxRequest() {
	}

	public OrderBoxRequest(UserSession userSession,String status) {
		this.status = status;

		this.userSession = userSession;

	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
