package com.chinarewards.gwt.elt.client.staffView.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.staffView.model.StaffWinClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月15日 11:09:22
 */
public class StaffWinResponse implements Result {


	private List<StaffWinClient> result;
	private int total;

	
	public List<StaffWinClient> getResult() {
		return result;
	}


	public void setResult(List<StaffWinClient> result) {
		this.result = result;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public StaffWinResponse() {

	}

	

}
