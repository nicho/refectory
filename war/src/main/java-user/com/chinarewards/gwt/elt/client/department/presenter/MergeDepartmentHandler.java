package com.chinarewards.gwt.elt.client.department.presenter;

import com.google.gwt.event.shared.EventHandler;

public interface MergeDepartmentHandler extends EventHandler {
	void mergeDepartment(String departmentIds, String departmentName,
			String leaderId);
}
