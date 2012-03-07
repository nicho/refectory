package com.chinarewards.elt.model.broadcast;

public enum OrganType {

	STAFF("员工"),

	DEPT("部门"), GROUP("小组"), ORG("机构");

	private final String displayName;

	OrganType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}