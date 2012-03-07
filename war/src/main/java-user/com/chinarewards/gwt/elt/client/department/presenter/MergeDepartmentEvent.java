package com.chinarewards.gwt.elt.client.department.presenter;

import com.google.gwt.event.shared.GwtEvent;

public class MergeDepartmentEvent extends GwtEvent<MergeDepartmentHandler> {

	private static Type<MergeDepartmentHandler> TYPE = new Type<MergeDepartmentHandler>();

	String departmentIds;
	String departmentName;
	String leaderId;

	public MergeDepartmentEvent() {

	}

	public MergeDepartmentEvent(String departmentIds, String departmentName,
			String leaderId) {
		this.departmentIds = departmentIds;
		this.departmentName = departmentName;
		this.leaderId = leaderId;
	}

	public static Type<MergeDepartmentHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MergeDepartmentHandler handler) {
		handler.mergeDepartment(departmentIds, departmentName, leaderId);
	}

	@Override
	public Type<MergeDepartmentHandler> getAssociatedType() {
		return getType();
	}
}
