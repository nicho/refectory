package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

/**
 * 获奖人
 * 
 * @author yanrui
 * 
 */
public class WinnerModelClient implements Serializable,
		Comparable<WinnerModelClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4815988602631452935L;

	/**
	 * 员工Id
	 */
	private String staffId;

	/**
	 * 员工姓名
	 */
	private String name;

	/**
	 * 员工部门
	 */
	private String dept;
	
	/**
	 * 状态
	 */
	private String status;


	

	public WinnerModelClient() {

	}


	public WinnerModelClient(String staffId, String name, String dept,String status) {
		this.staffId = staffId;
		this.name = name;
		this.dept = dept;
		this.status=status;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int compareTo(WinnerModelClient o) {
		return o == null ? -1 : o.staffId.compareTo(staffId);
	}

}
