package com.chinarewards.gwt.elt.client.dishesTypeList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.dishesTypeList.model.DishesTypeListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchDishesTypeListResponse implements Result {

	private List<DishesTypeListClient> result;
	private int total;



	public List<DishesTypeListClient> getResult() {
		return result;
	}



	public void setResult(List<DishesTypeListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchDishesTypeListResponse() {

	}
	public SearchDishesTypeListResponse(List<DishesTypeListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
