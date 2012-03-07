/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffView.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class StaffViewRequest implements Action<StaffViewResponse> {

	String staffId;
	


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}




	public StaffViewRequest() {
	}
	public StaffViewRequest(String staffId) {
		this.staffId=staffId;
	}


	


}
