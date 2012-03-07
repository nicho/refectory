package com.chinarewards.elt.model.org;

public enum NoticeMode {

	EMAIL("邮件"),

	SMS("短信");

	private String message;

	private NoticeMode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
