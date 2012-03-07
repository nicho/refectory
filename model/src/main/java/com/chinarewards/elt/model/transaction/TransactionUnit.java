package com.chinarewards.elt.model.transaction;

/**
 * 交易的积分单位,现阶段只有缤分这一种积分单位
 * 
 * @author roger
 * @since 1.0.0 2010-09-13
 */
public enum TransactionUnit {

	RMB("人民币"),

	BEANPOINTS("缤分");

	private final String displayName;

	TransactionUnit(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
