/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffAdd.request;

import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.support.UserSession;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class StaffAddRequest implements Action<StaffAddResponse> {
	private UserSession session;
	String staffId;
	String staffNo;
	String staffName;
	String departmentId;
	String photo;
	String jobPosition;
	String leadership;
	String phone;
	String email;
	Date dob;
	StaffStatus status;
	private List<UserRoleVo> userRoleVos;




	public List<UserRoleVo> getUserRoleVos() {
		return userRoleVos;
	}


	public void setUserRoleVos(List<UserRoleVo> userRoleVos) {
		this.userRoleVos = userRoleVos;
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


	public String getStaffNo() {
		return staffNo;
	}


	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}


	public String getStaffName() {
		return staffName;
	}


	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getJobPosition() {
		return jobPosition;
	}


	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}


	public String getLeadership() {
		return leadership;
	}


	public void setLeadership(String leadership) {
		this.leadership = leadership;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public StaffStatus getStatus() {
		return status;
	}


	public void setStatus(StaffStatus status) {
		this.status = status;
	}


	public StaffAddRequest() {
	}

	


}
