/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * StaffGenerateUser
 * 
 * @author nicho
 * @since 2012年2月15日 17:08:24
 */
public class UpdateUserPwdRequest implements Action<UpdateUserPwdResponse> {

	private String staffId;
	private String pwd;
	private UserSession session;


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


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


	public UpdateUserPwdRequest() {
	}


	public UpdateUserPwdRequest(String staffId,String pwd,UserSession session) {
		this.staffId = staffId;
		this.session=session;
		this.pwd=pwd;
	}


}
