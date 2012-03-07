package com.chinarewards.gwt.elt.client.staffList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchStaffListResponse implements Result {

	private List<StaffListClient> result;
	private int total;



	public List<StaffListClient> getResult() {
		return result;
	}



	public void setResult(List<StaffListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchStaffListResponse() {

	}
	public SearchStaffListResponse(List<StaffListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
