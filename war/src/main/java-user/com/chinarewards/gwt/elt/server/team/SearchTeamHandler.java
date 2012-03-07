package com.chinarewards.gwt.elt.server.team;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.vo.TeamListVo;
import com.chinarewards.elt.service.org.TeamService;
import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamRequest;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年1月10日 16:09:07
 */
public class SearchTeamHandler extends
		BaseActionHandler<SearchTeamRequest, SearchTeamResponse> {

	@InjectLogger
	Logger logger;

	TeamService teamService;

	@Inject
	public SearchTeamHandler(TeamService teamService) {
		this.teamService = teamService;

	}

	@Override
	public SearchTeamResponse execute(SearchTeamRequest request,
			ExecutionContext context) throws DispatchException {

		SearchTeamResponse resp = new SearchTeamResponse();

		TeamSearchVo teamSeacherVo = request.getTeamSearchVo();
		TeamListVo teamListVo = adapter(teamSeacherVo);//从客户端转到model
		PageStore<TeamListVo> teamPage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());
		teamListVo.setCorpid(request.getUserSession().getCorporationId());
		teamPage = teamService.teamList(uc, teamListVo);
		resp.setTotal(teamPage.getResultCount());
		resp.setResult(adapterToClient(teamPage.getResultList()));//从服务端转为客户端

		return resp;
	}

	private TeamListVo adapter(TeamSearchVo criteria) {
		TeamListVo vo = new TeamListVo();
		
		if (criteria.getName() != null) {
			vo.setName(criteria.getName());
		}
		
		
		if (criteria.getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(criteria.getPagination().getLimit());
			detail.setStart(criteria.getPagination().getStart());

			vo.setPaginationDetail(detail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			vo.setSortingDetail(sortingDetail);
		}
		return vo;
	}
	//从服务端得到的数据到客户端在列表显示的数据
		public static List<TeamSearchVo> adapterToClient(List<TeamListVo> service) {
			List<TeamSearchVo> resultList = new ArrayList<TeamSearchVo>();

			for (TeamListVo item : service) {
				TeamSearchVo client = new TeamSearchVo();
				client.setId(item.getId());
				client.setName(item.getName());
				client.setCode(item.getCode());
				client.setPeople(item.getPeople());
				client.setManager(item.getManager());
				resultList.add(client);
			}

			return resultList;
		}
	@Override
	public Class<SearchTeamRequest> getActionType() {
		return SearchTeamRequest.class;
	}

	@Override
	public void rollback(SearchTeamRequest req, SearchTeamResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
