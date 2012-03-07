package com.chinarewards.elt.model.information;


/**
 * The status of account.
 * 
 * @author nicho
 * @since 2012年2月14日 14:15:45
 * 
 */
public enum BroadcastingStatus {
	/* 已广播 */
	HASBROADCAST("已广播"),

	/*未广播 */
	NOTBROADCAST("未广播");



	private final String displayName;

	BroadcastingStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
