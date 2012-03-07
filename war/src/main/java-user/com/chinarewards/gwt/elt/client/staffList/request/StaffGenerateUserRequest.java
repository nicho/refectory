/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffList.request;

import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * StaffGenerateUser
 * 
 * @author nicho
 * @since 2012年2月15日 17:08:24
 */
public class StaffGenerateUserRequest implements Action<StaffGenerateUserResponse> {

	private String staffId;
	private UserSession session;


	public UserSession getSession() {
		return session;
	}


	public void setSession(UserSession session) {
		this.session = session;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public StaffGenerateUserRequest() {
	}


	public StaffGenerateUserRequest(String staffId,UserSession session) {
		this.staffId = staffId;
		this.session=session;
	}


}
