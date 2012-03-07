/**
 * 
 */
package com.chinarewards.gwt.elt.client.staff.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.staff.model.StaffVo;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2011年12月7日 09:40:32
 */
public class HrRegisterRequest implements Action<HrRegisterResponse> {

	private StaffVo staffvo;


	public StaffVo getStaffvo() {
		return staffvo;
	}

	public void setStaffvo(StaffVo staffvo) {
		this.staffvo = staffvo;
	}

	public HrRegisterRequest() {
	}

	/**
	 * 
	 * @param StaffListVo
	 */
	public HrRegisterRequest(StaffVo staffvo) {
		this.staffvo = staffvo;
	}


}
