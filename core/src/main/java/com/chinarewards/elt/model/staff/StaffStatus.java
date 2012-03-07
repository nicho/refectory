package com.chinarewards.elt.model.staff;


/**
 * The status of account.
 * 
 * @author nicho
 * @since 2012年2月14日 14:15:45
 * 
 */
public enum StaffStatus {
	/* 待入职 */
	ENTRY("待入职"),

	/* 在职 */
	JOB("在职"),
	
	/*已离职 */
	DEPARTURE("已离职");


	private final String displayName;

	StaffStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
