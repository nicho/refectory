package com.chinarewards.gwt.elt.client.message.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.message.model.MessageListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchMessageListResponse implements Result {

	private List<MessageListClient> result;
	private int total;



	public List<MessageListClient> getResult() {
		return result;
	}



	public void setResult(List<MessageListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchMessageListResponse() {

	}
	public SearchMessageListResponse(List<MessageListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
