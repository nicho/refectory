package com.chinarewards.gwt.elt.server.login;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.user.UserSessionVo;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.login.TokenValidRequest;
import com.chinarewards.gwt.elt.client.login.TokenValidResponse;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

public class TokenValidActionHandler extends
		BaseActionHandler<TokenValidRequest, TokenValidResponse> {

	UserService userService;
	
	@Inject
	public TokenValidActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	@Override
	public Class<TokenValidRequest> getActionType() {
		return TokenValidRequest.class;
	}

	@Override
	public TokenValidResponse execute(TokenValidRequest action, ExecutionContext context)
			throws DispatchException {
		TokenValidResponse tokenRep=new TokenValidResponse();
		UserSessionVo userSessionVo=userService.tokenVaild(action.getToken());
		if(userSessionVo !=null){
		tokenRep.setCorporationId(userSessionVo.getCorporationId());
		tokenRep.setLoginName(userSessionVo.getUsername());
		tokenRep.setToken(userSessionVo.getId());
		tokenRep.setUserRoles(UserRoleTool.adaptToRoleVo(userSessionVo.getUserRoles()));
		tokenRep.setDepartmentId(userSessionVo.getDepartmentId());
		tokenRep.setStaffId(userSessionVo.getStaffId());
		tokenRep.setCorporationName(userSessionVo.getCorporationName());
		tokenRep.setPhoto(userSessionVo.getPhoto());
		if(userSessionVo.getLastLoginRole()!=null)
		{
			tokenRep.setLastLoginRole(UserRoleVo.valueOf(userSessionVo.getLastLoginRole().toString()));
		}
		else
		{
			List<UserRole> roles=userSessionVo.getUserRoles();
			if(roles.size()>0)
			{
				if(roles.contains(UserRole.CORP_ADMIN))
					tokenRep.setLastLoginRole(UserRoleVo.valueOf(UserRole.CORP_ADMIN.toString()));
				else if(roles.contains(UserRole.DEPT_MGR))
					tokenRep.setLastLoginRole(UserRoleVo.valueOf(UserRole.DEPT_MGR.toString()));
				else if(roles.contains(UserRole.GIFT))
					tokenRep.setLastLoginRole(UserRoleVo.valueOf(UserRole.GIFT.toString()));
				else if(roles.contains(UserRole.STAFF))
					tokenRep.setLastLoginRole(UserRoleVo.valueOf(UserRole.STAFF.toString()));
			}
		 }
		}
		return tokenRep;
	}

	@Override
	public void rollback(TokenValidRequest action, TokenValidResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
