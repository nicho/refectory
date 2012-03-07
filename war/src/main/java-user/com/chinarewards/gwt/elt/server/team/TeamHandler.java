package com.chinarewards.gwt.elt.server.team;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.model.org.search.TeamParam;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.TeamService;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;
import com.chinarewards.gwt.elt.client.team.request.TeamAddRequest;
import com.chinarewards.gwt.elt.client.team.request.TeamAddResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class TeamHandler extends
		BaseActionHandler<TeamAddRequest, TeamAddResponse> {

	@InjectLogger
	Logger logger;
	TeamService teamService;

	@Inject
	public TeamHandler(TeamService teamService) {
		this.teamService = teamService;
	}

	@Override
	public Class<TeamAddRequest> getActionType() {
		return TeamAddRequest.class;
	}

	@Override
	public TeamAddResponse execute(TeamAddRequest action,
			ExecutionContext context) throws DispatchException {
	
		TeamVo teamVo = action.getTeamVo();
		TeamParam teamParam = assembleTeamModel(teamVo);
		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession().getUserRoles()));
		Team AddItem = teamService.save(uc, teamParam);

		return new TeamAddResponse(AddItem.getId());
	}
    
	/**
	 * Convert from GiftVo to GeneratorGiftModel.
	 */
	public static TeamParam assembleTeamModel(TeamVo teamVo) {
		TeamParam teamParam = new TeamParam();
		teamParam.setId(teamVo.getId());
		teamParam.setName(teamVo.getName());
		teamParam.setDescription(teamVo.getDescription());
		//得到负责人ID
     	ParticipateInfoClient staff = teamVo.getFzInfo();
      	List<String> orgId = new ArrayList<String>();
      	List<OrganicationClient> orgs = ((SomeoneClient) staff).getOrganizations();
      		for (OrganicationClient org : orgs) {
      			orgId.add(org.getId());
      		}
      	teamParam.setFzIds(orgId);  
      	
      //得到所选成员ID
     	ParticipateInfoClient staffs = teamVo.getMemberInfo();
      	List<String> orgIds = new ArrayList<String>();
      	List<OrganicationClient> orgss = ((SomeoneClient) staffs).getOrganizations();
      		for (OrganicationClient org : orgss) {
      			orgIds.add(org.getId());
      		}
      	teamParam.setMembersIds(orgIds);  
		
		return teamParam;
	}

	@Override
	public void rollback(TeamAddRequest action, TeamAddResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
