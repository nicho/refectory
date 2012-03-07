/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;

/**
 * @author lw
 * @since
 */
public class SearchOrderByIdResponse implements Result {

	private OrderViewClient orderViewClient;

	public SearchOrderByIdResponse() {

	}

	public SearchOrderByIdResponse(OrderViewClient orderViewClient) {
		this.orderViewClient = orderViewClient;
	}

	public OrderViewClient getOrderViewClient() {
		return orderViewClient;
	}

	public void setOrderViewClient(OrderViewClient orderViewClient) {
		this.orderViewClient = orderViewClient;
	}

}
