package com.chinarewards.elt.model.information;

/**
 * The status of account.
 * 
 * @author nicho
 * @since 2012年2月14日 14:15:45
 * 
 */
public enum BroadcastMessage {

	BROADCASTING("广播"),

	MESSAGE("消息");

	private final String displayName;

	BroadcastMessage(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
