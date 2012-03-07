package com.chinarewards.gwt.elt.server.user;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.staffList.request.UpdateUserPwdRequest;
import com.chinarewards.gwt.elt.client.staffList.request.UpdateUserPwdResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the UpdateUserPwdRequest.
 * 
 * @author nicho
 * @since 2012年2月16日 14:57:56
 */
public class UpdateUserPwdActionHandler extends
		BaseActionHandler<UpdateUserPwdRequest, UpdateUserPwdResponse> {

	@InjectLogger
	Logger logger;
	UserService userService;

	@Inject
	public UpdateUserPwdActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UpdateUserPwdResponse execute(UpdateUserPwdRequest request,
			ExecutionContext response) throws DispatchException {

		String message = userService.updateUserPwd(request.getStaffId(),
				request.getPwd(), request.getSession().getToken());
		return new UpdateUserPwdResponse(message);

	}

	@Override
	public Class<UpdateUserPwdRequest> getActionType() {
		return UpdateUserPwdRequest.class;
	}

	@Override
	public void rollback(UpdateUserPwdRequest request,
			UpdateUserPwdResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
