package com.chinarewards.gwt.elt.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.gwt.elt.client.login.LoginRequest;
import com.chinarewards.gwt.elt.client.login.LoginResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;

public class LoginActionHandler extends
		BaseActionHandler<LoginRequest, LoginResponse> {
	// UserService userService;
	//
	// @Inject
	// public LoginActionHandler(UserService userService) {
	// this.userService = userService;
	// }
	public LoginActionHandler() {

	}

	@Override
	public Class<LoginRequest> getActionType() {
		return LoginRequest.class;
	}

	@Override
	public LoginResponse execute(LoginRequest action, ExecutionContext context)
			throws DispatchException {

		LoginResponse resp = new LoginResponse();
		// UserSessionVo u =
		// userService.authenticate(action.getUserName(),action.getPwd());
		//
		// resp.setCorporationId(u.getCorporationId());
		// resp.setCorporationName(u.getCorporationName());
		// resp.setPhoto(u.getPhoto());
		// resp.setLoginName(u.getUsername());
		// resp.setToken(u.getId());
		// resp.setDepartmentId(u.getDepartmentId());
		// resp.setUserRoles(UserRoleTool.adaptToRoleVo(u.getUserRoles()));
		// resp.setStaffId(u.getStaffId());
		//

		return resp;

	}

	@Override
	public void rollback(LoginRequest action, LoginResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
