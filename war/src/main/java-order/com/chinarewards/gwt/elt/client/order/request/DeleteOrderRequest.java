/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import com.chinarewards.gwt.elt.client.order.request.DeleteOrderResponse;
import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class DeleteOrderRequest implements Action<DeleteOrderResponse> {

	private String orderId;
	private UserSession userSession;

	public DeleteOrderRequest() {
	}

	public DeleteOrderRequest(String orderId, UserSession userSession) {
		this.orderId = orderId;

		this.userSession = userSession;

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
