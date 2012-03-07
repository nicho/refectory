package com.chinarewards.gwt.elt.client.staffHeavenIndex.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class StaffHeavenIndexResponse implements Result {

	private List<StaffHeavenIndexClient> result;
	private int total;



	public List<StaffHeavenIndexClient> getResult() {
		return result;
	}



	public void setResult(List<StaffHeavenIndexClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public StaffHeavenIndexResponse() {

	}
	public StaffHeavenIndexResponse(List<StaffHeavenIndexClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
