package com.chinarewards.gwt.elt.client.broadcastReply.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchBroadcastReplyResponse implements Result {

	private List<ReplyListClient> result;
	private int total;



	public List<ReplyListClient> getResult() {
		return result;
	}



	public void setResult(List<ReplyListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchBroadcastReplyResponse() {

	}
	public SearchBroadcastReplyResponse(List<ReplyListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
