package com.chinarewards.gwt.elt.server.staffPassword;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.password.request.PasswordRequest;
import com.chinarewards.gwt.elt.client.password.request.PasswordResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the PasswordRequest.
 * 
 * @author nicho
 * @since 2012年2月16日 14:57:56
 */
public class UpdateStaffPwdActionHandler extends
		BaseActionHandler<PasswordRequest, PasswordResponse> {

	@InjectLogger
	Logger logger;
	UserService userService;

	@Inject
	public UpdateStaffPwdActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public PasswordResponse execute(PasswordRequest request,
			ExecutionContext response) throws DispatchException {

		String message = userService.updateStaffPwd(request.getStaffId(),request.getOldpwd(),
				request.getPwd(), request.getSession().getToken());
		return new PasswordResponse(message);

	}

	@Override
	public Class<PasswordRequest> getActionType() {
		return PasswordRequest.class;
	}

	@Override
	public void rollback(PasswordRequest request,
			PasswordResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
