package com.chinarewards.gwt.elt.client.userList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.userList.model.UserListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchUserListResponse implements Result {

	private List<UserListClient> result;
	private int total;



	public List<UserListClient> getResult() {
		return result;
	}



	public void setResult(List<UserListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchUserListResponse() {

	}
	public SearchUserListResponse(List<UserListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
