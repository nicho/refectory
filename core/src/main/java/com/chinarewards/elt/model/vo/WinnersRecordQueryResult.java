package com.chinarewards.elt.model.vo;

import java.util.Date;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;

public class WinnersRecordQueryResult {

	private String staffName;
	private String staffId;
	private String depName;
	private String depId;
	private int count;
	private Date lastWinnerTime;

	

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getLastWinnerTime() {
		return lastWinnerTime;
	}

	public void setLastWinnerTime(Date lastWinnerTime) {
		this.lastWinnerTime = lastWinnerTime;
	}

}
