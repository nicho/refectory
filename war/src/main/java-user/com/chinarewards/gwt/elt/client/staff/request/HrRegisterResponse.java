package com.chinarewards.gwt.elt.client.staff.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.user.model.UserSearchResultVo;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2011年12月7日 09:40:22
 */
public class HrRegisterResponse implements Result {

	private String staffId;
	private String userId;



	public HrRegisterResponse() {

	}

	public HrRegisterResponse(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
