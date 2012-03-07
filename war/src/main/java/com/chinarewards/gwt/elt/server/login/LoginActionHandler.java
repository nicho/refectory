package com.chinarewards.gwt.elt.server.login;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.user.UserSessionVo;
import com.chinarewards.elt.model.user.UserStatus;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.login.LoginRequest;
import com.chinarewards.gwt.elt.client.login.LoginResponse;
import com.chinarewards.gwt.elt.model.ClientException;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

public class LoginActionHandler extends
		BaseActionHandler<LoginRequest, LoginResponse> {
	UserService userService;

	@Inject
	public LoginActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Class<LoginRequest> getActionType() {
		return LoginRequest.class;
	}

	@Override
	public LoginResponse execute(LoginRequest action, ExecutionContext context)
			throws DispatchException {

		LoginResponse resp = new LoginResponse();
		UserSessionVo u = userService.authenticate(action.getUserName(),
				action.getPwd());
		
		if (u != null) {
			if(u.getUserRoles().size()<=0)
			{
				throw new ClientException("用户无角色!");
			}
			if(u.getUserStatus()==UserStatus.Inactive)
			{
				throw new ClientException("您已离职,拒绝进入!");
			}
			resp.setCorporationId(u.getCorporationId());
			resp.setCorporationName(u.getCorporationName());
			resp.setPhoto(u.getPhoto());
			resp.setLoginName(u.getUsername());
			resp.setToken(u.getId());
			resp.setDepartmentId(u.getDepartmentId());
			resp.setUserRoles(UserRoleTool.adaptToRoleVo(u.getUserRoles()));
			resp.setStaffId(u.getStaffId());
			if(u.getLastLoginRole()!=null)
			{
				resp.setLastLoginRole(UserRoleVo.valueOf(u.getLastLoginRole().toString()));
			}
			else
			{
				List<UserRole> roles=u.getUserRoles();
				if(roles.size()>0)
				{
					if(roles.contains(UserRole.CORP_ADMIN))
						resp.setLastLoginRole(UserRoleVo.valueOf(UserRole.CORP_ADMIN.toString()));
					else if(roles.contains(UserRole.DEPT_MGR))
						resp.setLastLoginRole(UserRoleVo.valueOf(UserRole.DEPT_MGR.toString()));
					else if(roles.contains(UserRole.GIFT))
						resp.setLastLoginRole(UserRoleVo.valueOf(UserRole.GIFT.toString()));
					else if(roles.contains(UserRole.STAFF))
						resp.setLastLoginRole(UserRoleVo.valueOf(UserRole.STAFF.toString()));
				}
			}
		} else {
			throw new ClientException("用户名或密码错误!");
		}
		
		return resp;

	}

	@Override
	public void rollback(LoginRequest action, LoginResponse result,
			ExecutionContext context) throws DispatchException {

	}


}
