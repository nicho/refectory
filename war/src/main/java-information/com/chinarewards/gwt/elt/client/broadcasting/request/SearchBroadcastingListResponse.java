package com.chinarewards.gwt.elt.client.broadcasting.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchBroadcastingListResponse implements Result {

	private List<BroadcastingListClient> result;
	private int total;



	public List<BroadcastingListClient> getResult() {
		return result;
	}



	public void setResult(List<BroadcastingListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchBroadcastingListResponse() {

	}
	public SearchBroadcastingListResponse(List<BroadcastingListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
