/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderSubmit.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年1月31日 18:52:22
 */
public class OrderSubmitRequest implements Action<OrderSubmitResponse> {


	private String orderId;
	private String userId;




	public OrderSubmitRequest() {
	}

	public OrderSubmitRequest(String orderId,String userId) {
		this.orderId = orderId;
		this.userId=userId;

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
