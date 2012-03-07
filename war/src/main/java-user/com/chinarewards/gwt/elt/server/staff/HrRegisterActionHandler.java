package com.chinarewards.gwt.elt.server.staff;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.staff.request.HrRegisterRequest;
import com.chinarewards.gwt.elt.client.staff.request.HrRegisterResponse;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the HrRegisterRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class HrRegisterActionHandler extends
		BaseActionHandler<HrRegisterRequest, HrRegisterResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public HrRegisterActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public HrRegisterResponse execute(HrRegisterRequest request,
			ExecutionContext response) throws DispatchException {

		HrRegisterResponse hrResponse = new HrRegisterResponse();
		StaffUserProcess process = new StaffUserProcess();
		process.setUsername(request.getStaffvo().getUsername());
		process.setName(request.getStaffvo().getName());
		process.setEmail(request.getStaffvo().getEmail());
		process.setPassword(request.getStaffvo().getPassword());
		process.setTell(request.getStaffvo().getTell());
		process.setCreateUserId(request.getStaffvo().getCreateUserId());
		process.setDeptId(request.getStaffvo().getDeptId());

		List<UserRole> roles=new ArrayList<UserRole>();
		for (int i = 0; i < request.getStaffvo().getUserRoleVos().size(); i++) {
			roles.add(UserRole.valueOf(request.getStaffvo().getUserRoleVos().get(i).toString()));
		}
		process.setRoles(roles);
		String userId=staffService.createStaff(process);
		hrResponse.setUserId(userId);
		return hrResponse;
	}

	@Override
	public Class<HrRegisterRequest> getActionType() {
		return HrRegisterRequest.class;
	}

	@Override
	public void rollback(HrRegisterRequest request,
			HrRegisterResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
