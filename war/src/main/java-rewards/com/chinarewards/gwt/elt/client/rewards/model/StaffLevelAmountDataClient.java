package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

/**
 * Define every staff level can get how many money if getting reward.
 * 
 * @author yanxin
 * @since 0.2.0 2010-01-17
 */
public class StaffLevelAmountDataClient implements Serializable,
		Comparable<StaffLevelAmountDataClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587365427777330326L;

	private String staffLevelId;

	private String staffLevelName;

	private double amount;

	public StaffLevelAmountDataClient() {
	}

	public StaffLevelAmountDataClient(String staffLevelId,
			String staffLevelName, double amount) {
		this.staffLevelId = staffLevelId;
		this.staffLevelName = staffLevelName;
		this.amount = amount;
	}

	public String getStaffLevelId() {
		return staffLevelId;
	}

	public void setStaffLevelId(String staffLevelId) {
		this.staffLevelId = staffLevelId;
	}

	public String getStaffLevelName() {
		return staffLevelName;
	}

	public void setStaffLevelName(String staffLevelName) {
		this.staffLevelName = staffLevelName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(StaffLevelAmountDataClient o) {
		return o != null ? o.getStaffLevelId().compareTo(staffLevelId) : -1;
	}
}
