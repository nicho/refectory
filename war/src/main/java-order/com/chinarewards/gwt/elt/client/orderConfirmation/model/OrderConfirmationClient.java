package com.chinarewards.gwt.elt.client.orderConfirmation.model;

import java.io.Serializable;

public class OrderConfirmationClient implements Serializable, Comparable<OrderConfirmationClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

/**
 * 礼品ID
 */
	private String orderId;

	public OrderConfirmationClient() {

	}

	public OrderConfirmationClient(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	@Override
	public int compareTo(OrderConfirmationClient o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
