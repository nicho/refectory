package com.chinarewards.gwt.elt.client.staffView.request;

import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class StaffViewResponse implements Result {


	String staffId;
	String staffNo;
	String staffName;
	String departmentId;
	String departmentName;
	String photo;
	String jobPosition;
	String leadership;
	String phone;
	String email;
	Date dob;
	StaffStatus status;
    int leadTime;//颁奖提前通知时间
	private List<UserRoleVo> userRoleVos;



	public List<UserRoleVo> getUserRoleVos() {
		return userRoleVos;
	}


	public void setUserRoleVos(List<UserRoleVo> userRoleVos) {
		this.userRoleVos = userRoleVos;
	}


	public int getLeadTime() {
		return leadTime;
	}


	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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




	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public StaffViewResponse() {

	}

	

}
