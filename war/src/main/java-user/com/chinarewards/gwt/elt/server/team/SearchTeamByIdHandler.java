package com.chinarewards.gwt.elt.server.team;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Members;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.model.vo.TeamListVo;
import com.chinarewards.elt.service.org.TeamService;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamByIdRequest;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamByIdResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年1月10日 16:09:07
 */
public class SearchTeamByIdHandler extends
		BaseActionHandler<SearchTeamByIdRequest, SearchTeamByIdResponse> {

	@InjectLogger
	Logger logger;

	TeamService teamService;

	@Inject
	public SearchTeamByIdHandler(TeamService teamService) {
		this.teamService = teamService;

	}

	@Override
	public SearchTeamByIdResponse execute(SearchTeamByIdRequest request,
			ExecutionContext context) throws DispatchException {

		SearchTeamByIdResponse resp = new SearchTeamByIdResponse();

		TeamListVo teamListVo = teamService.findTeamById(request.getId());
		
		return new SearchTeamByIdResponse(adapterToClient(teamListVo));
	}

		
	//从服务端得到的数据到客户端在列表显示的数据
		public static TeamVo adapterToClient(TeamListVo item) {
			   TeamVo client = new TeamVo();
				client.setId(item.getId());
				client.setName(item.getName());
				client.setDescription(item.getDescription());
				// 管理人员
				List<Members> managerList = item.getManagersList();
				ParticipateInfoClient managers = null;
				List<OrganicationClient> orgs = getOrgsFromManager(managerList);
				managers = new SomeoneClient(orgs);
				client.setFzInfo(managers);
				
				//成员
				List<Members> memberList = item.getMembersList();
				ParticipateInfoClient members = null;
				List<OrganicationClient> org = getOrgsFromManager(memberList);
				members = new SomeoneClient(org);
				client.setMemberInfo(members);
				
			return client;
		}
	public static  List<OrganicationClient> getOrgsFromManager(List<Members> managerList) {
		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
		for (Members p : managerList) {
			orgs.add(new OrganicationClient(p.getStaff().getId(), p.getStaff().getName()));
		}
		return orgs;
	}	
	@Override
	public Class<SearchTeamByIdRequest> getActionType() {
		return SearchTeamByIdRequest.class;
	}

	@Override
	public void rollback(SearchTeamByIdRequest req, SearchTeamByIdResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
