package com.chinarewards.gwt.elt.server.core;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.core.request.StaffInitRequest;
import com.chinarewards.gwt.elt.client.core.request.StaffInitResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.google.inject.Inject;

public class StaffInitActionHandler extends
		BaseActionHandler<StaffInitRequest, StaffInitResponse> {
	IStaffService staffService;

	@Inject
	public StaffInitActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public Class<StaffInitRequest> getActionType() {
		return StaffInitRequest.class;
	}

	@Override
	public StaffInitResponse execute(StaffInitRequest action, ExecutionContext context)
			throws DispatchException {

		StaffInitResponse resp = new StaffInitResponse();
		Staff staff=staffService.findStaffById(action.getStaffId());

		resp.setIntegral((int)staffService.getBalance(action.getStaffId()));
		resp.setName(staff.getName());
		if(staff.getDepartment()!=null && staff.getDepartment().getParent()!=null)
		resp.setDeptName(staff.getDepartment().getName());
		resp.setPhoto(staff.getPhoto());
		resp.setSex("");
		resp.setStation(staff.getJobPosition());
		return resp;

	}

	@Override
	public void rollback(StaffInitRequest action, StaffInitResponse result,
			ExecutionContext context) throws DispatchException {

	}


}
