/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author lw
 * @since 2012年1月31日 18:52:22
 */
public class OrderViewRequest implements Action<OrderViewResponse> {


	private String orderId;
	private String userId;
    private String stauts;



	
	public OrderViewRequest() {
	}

	public OrderViewRequest(String orderId,String userId,String status) {
		this.orderId = orderId;
		this.userId=userId;
        this.stauts = status;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
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
