package com.chinarewards.gwt.elt.client.restaurantList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.restaurantList.model.RestaurantListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchRestaurantListResponse implements Result {

	private List<RestaurantListClient> result;
	private int total;



	public List<RestaurantListClient> getResult() {
		return result;
	}



	public void setResult(List<RestaurantListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchRestaurantListResponse() {

	}
	public SearchRestaurantListResponse(List<RestaurantListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
