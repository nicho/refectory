/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdResponse;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author lw
 */
public class SearchOrderByIdRequest implements
		Action<SearchOrderByIdResponse> {

	private String id;
    private String staffId;
	public SearchOrderByIdRequest() {
	}

	public SearchOrderByIdRequest(String id,String staffId) {
		this.id = id;
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
