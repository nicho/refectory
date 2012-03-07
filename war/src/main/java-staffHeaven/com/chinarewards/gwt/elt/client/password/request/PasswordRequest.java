/**
 * 
 */
package com.chinarewards.gwt.elt.client.password.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * StaffGenerateUser
 * 
 * @author lw
 * @since 2012年2月15日 17:08:24
 */
public class PasswordRequest implements Action<PasswordResponse> {

	private String staffId;
	private String pwd;
	private UserSession session;
    private String oldpwd;

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


	public PasswordRequest() {
	}


	public PasswordRequest(String staffId,String oldpwd,String pwd,UserSession session) {
		this.staffId = staffId;
		this.session=session;
		this.pwd=pwd;
		this.oldpwd = oldpwd;
	}


	public String getOldpwd() {
		return oldpwd;
	}


	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}


}
