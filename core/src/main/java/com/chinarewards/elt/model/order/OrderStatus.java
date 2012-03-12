package com.chinarewards.elt.model.order;

public enum OrderStatus {

	UNHANDLED("未处理"),

	SUCCESS("成功"), FAILURE("失败"), NOTCONSUMPR("未消费"), HASCONSUMER("已消费");

	private final String displayName;

	OrderStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
