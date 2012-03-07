/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author yanrui
 */
public class UpdateOrderStatusRequest implements Action<UpdateOrderStatusResponse> {

	private String orderId;
	private String userId;
	private OrderStatus status;
	private UserSession userSession;


	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public UpdateOrderStatusRequest() {
	}

	public UpdateOrderStatusRequest(String orderId,String userId,OrderStatus status,UserSession userSession) {
		this.orderId = orderId;
		this.userId=userId;
		this.status=status;
        this.userSession = userSession;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
