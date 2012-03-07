package com.chinarewards.gwt.elt.server.user;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.TeamService;
import com.chinarewards.gwt.elt.client.team.request.DeleteTeamRequest;
import com.chinarewards.gwt.elt.client.team.request.DeleteTeamResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DeleteTeamByIdHandler extends
		BaseActionHandler<DeleteTeamRequest, DeleteTeamResponse> {

	@InjectLogger
	Logger logger;

	TeamService teamService;

	@Inject
	public DeleteTeamByIdHandler(TeamService teamService) {
		this.teamService = teamService;

	}

	@Override
	public DeleteTeamResponse execute(DeleteTeamRequest request,
			ExecutionContext context) throws DispatchException {
		
		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());

		String totle="";
		try {
			totle = teamService.deleteTeam(request.getTeamId());
		} catch (Exception e) {
			totle="";
		}		
		
		return new DeleteTeamResponse(totle);
	}

	
	@Override
	public Class<DeleteTeamRequest> getActionType() {
		return DeleteTeamRequest.class;
	}

	@Override
	public void rollback(DeleteTeamRequest req, DeleteTeamResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
