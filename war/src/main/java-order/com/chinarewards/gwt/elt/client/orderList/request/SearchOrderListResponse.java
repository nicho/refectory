package com.chinarewards.gwt.elt.client.orderList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.orderList.model.OrderListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchOrderListResponse implements Result {

	private List<OrderListClient> result;
	private int total;



	public List<OrderListClient> getResult() {
		return result;
	}



	public void setResult(List<OrderListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchOrderListResponse() {

	}
	public SearchOrderListResponse(List<OrderListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
