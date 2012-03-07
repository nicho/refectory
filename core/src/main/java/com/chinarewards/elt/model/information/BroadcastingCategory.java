package com.chinarewards.elt.model.information;

/**
 * The status of account.
 * 
 * @author nicho
 * @since 2012年2月14日 14:15:45
 * 
 */
public enum BroadcastingCategory {
	/* 公司广播 */
	COMPANYBROADCAST("公司广播"), STAFFBROADCAST("员工广播"),

	SYSBROADCAST("系统广播"),

	REWARDBROADCAST("奖励广播"),
	QUIETLYINFORMATION("悄悄话"),
	DALLIANCEINFORMATION("调戏信息"),
	THEMEBROADCAST("主题广播"),
	/* 其他广播 */
	OTHERBROADCAST("其他广播");

	private final String displayName;

	BroadcastingCategory(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
