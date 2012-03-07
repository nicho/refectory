package com.chinarewards.gwt.elt.client.orderHistory.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class OrderHistoryViewRequest implements Action<OrderHistoryViewResponse> {


	private String orderId;
	private String userId;
    private String stauts;

	public OrderHistoryViewRequest() {
	}
	
	public OrderHistoryViewRequest(String orderId,String userId,String stauts) {
		this.orderId = orderId;
		this.userId=userId;
		this. stauts = stauts;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	public OrderHistoryViewRequest(String orderId) {
		this.orderId = orderId;
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
