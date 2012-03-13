package com.chinarewards.gwt.elt.client.dishesList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.dishesList.model.DishesListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchDishesListResponse implements Result {

	private List<DishesListClient> result;
	private int total;



	public List<DishesListClient> getResult() {
		return result;
	}



	public void setResult(List<DishesListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchDishesListResponse() {

	}
	public SearchDishesListResponse(List<DishesListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
