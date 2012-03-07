package com.chinarewards.gwt.elt.server.staff;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.GeneratedUserConstants;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.staffList.request.StaffGenerateUserRequest;
import com.chinarewards.gwt.elt.client.staffList.request.StaffGenerateUserResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the StaffGenerateUserRequest.
 * 
 * @author nicho
 * @since 2012年2月15日 17:10:05
 */
public class StaffGenerateUserActionHandler extends
		BaseActionHandler<StaffGenerateUserRequest, StaffGenerateUserResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;

	@Inject
	public StaffGenerateUserActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public StaffGenerateUserResponse execute(StaffGenerateUserRequest request,
			ExecutionContext response) throws DispatchException {

		UserContext context = new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		GeneratedUserConstants constants = staffService.generatedUserbyStaff(request.getStaffId(), context);

		return new StaffGenerateUserResponse(constants.getDisplayName());
	}

	@Override
	public Class<StaffGenerateUserRequest> getActionType() {
		return StaffGenerateUserRequest.class;
	}

	@Override
	public void rollback(StaffGenerateUserRequest request,
			StaffGenerateUserResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
