package com.chinarewards.gwt.elt.client.staff.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.staff.model.StaffVo;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class LeadTimeResponse implements Result {

	StaffVo staffVo;
	public StaffVo getStaffVo() {
		return staffVo;
	}
	public void setStaffVo(StaffVo staffVo) {
		this.staffVo = staffVo;
	}
	public LeadTimeResponse() {

	}
	public LeadTimeResponse(StaffVo staffVo) {
		this.staffVo=staffVo;

	}

	

}
