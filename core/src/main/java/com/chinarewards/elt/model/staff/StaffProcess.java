package com.chinarewards.elt.model.staff;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.user.UserRole;

public class StaffProcess {

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
	String corpId;
	String userName;
	String password;
	private List<UserRole> UserRoleVos;

	public List<UserRole> getUserRoleVos() {
		return UserRoleVos;
	}


	public void setUserRoleVos(List<UserRole> userRoleVos) {
		UserRoleVos = userRoleVos;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
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
	
}
