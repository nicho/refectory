package com.chinarewards.gwt.elt.server.chooseOrganization;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.staff.StaffSearchCriteria;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.vo.TeamListVo;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.elt.service.org.TeamService;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria.OrganType;
import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchResult;
import com.chinarewards.gwt.elt.client.chooseOrganization.request.ChooseOrganizationRequest;
import com.chinarewards.gwt.elt.client.chooseOrganization.request.ChooseOrganizationResponse;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the ChooseOrganizationRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class ChooseOrganizationListActionHandler
		extends
		BaseActionHandler<ChooseOrganizationRequest, ChooseOrganizationResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;
	DepartmentService departmentService;
	CorporationService corporationService;
	TeamService teamService;

	@Inject
	public ChooseOrganizationListActionHandler(IStaffService staffService,
			DepartmentService departmentService,
			CorporationService corporationService, TeamService teamService) {
		this.staffService = staffService;
		this.departmentService = departmentService;
		this.corporationService = corporationService;
		this.teamService = teamService;
	}

	@Override
	public ChooseOrganizationResponse execute(
			ChooseOrganizationRequest request, ExecutionContext response)
			throws DispatchException {

		ChooseOrganizationResponse staffResponse = new ChooseOrganizationResponse();
		if(request.getCriteria().getOrganType()==OrganType.STAFF)
		{
			return queryStaff(request);
		}
		else if(request.getCriteria().getOrganType()==OrganType.DEPT)
		{
			return queryDept(request);
		}
		else if(request.getCriteria().getOrganType()==OrganType.GROUP)
		{
			return queryGroup(request);
		}
		else if(request.getCriteria().getOrganType()==OrganType.ORG)
		{
			return queryOrg(request);
		}
		return staffResponse;
	}
	private ChooseOrganizationResponse queryGroup(ChooseOrganizationRequest request)
	{
		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		
		TeamListVo teamListVo=new TeamListVo();
		if (request.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(request.getCriteria().getPagination().getLimit());
			detail.setStart(request.getCriteria().getPagination().getStart());

			teamListVo.setPaginationDetail(detail);
		}
		if(request.getCriteria().getKey()!=null)
			teamListVo.setName(request.getCriteria().getKey());
		
		if (request.getCriteria().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(request.getCriteria().getSorting().getSort());
			sortingDetail.setDirection(request.getCriteria().getSorting().getDirection());
			teamListVo.setSortingDetail(sortingDetail);
		}
		
		OrganSearchResult rs=new OrganSearchResult();
		List<OrganicationClient> lt=new ArrayList<OrganicationClient>();

		PageStore<TeamListVo>  volist=teamService.teamList(context, teamListVo);
		for (TeamListVo team:volist.getResultList()) {
			OrganicationClient client=new OrganicationClient();
			client.setId(team.getId());
			client.setName(team.getName());
			client.setType(OrganType.GROUP);
			lt.add(client);
		}

		
		rs.setResult(lt);
		rs.setTotal(volist.getResultCount());
		return new ChooseOrganizationResponse(rs);
	}
	private ChooseOrganizationResponse queryOrg(ChooseOrganizationRequest request)
	{
		OrganSearchResult rs=new OrganSearchResult();
		List<OrganicationClient> lt=new ArrayList<OrganicationClient>();
		OrganicationClient client=new OrganicationClient();
		Corporation corp=corporationService.findCorporationById(request.getSession().getCorporationId());
		client.setId(corp.getId());
		client.setName(corp.getName());
		client.setType(OrganType.ORG);
		lt.add(client);
		
		rs.setResult(lt);
		rs.setTotal(1);
		return new ChooseOrganizationResponse(rs);
	}
	private ChooseOrganizationResponse queryDept(ChooseOrganizationRequest request)
	{
		List<Department> result=departmentService.getDepartmentsOfCorporationAndKey(request.getSession().getCorporationId(),request.getCriteria().getKey());
		List<OrganicationClient> lt=new ArrayList<OrganicationClient>();
		for (Department dept:result) {
			OrganicationClient client=new OrganicationClient();
			client.setId(dept.getId());
			client.setName(dept.getName());
			client.setType(OrganType.DEPT);
			lt.add(client);
		}
		OrganSearchResult rs=new OrganSearchResult();
		rs.setResult(lt);
		rs.setTotal(result.size());
		return new ChooseOrganizationResponse(rs);
	}
	private ChooseOrganizationResponse queryStaff(ChooseOrganizationRequest request)
	{
		StaffSearchCriteria criteria=new StaffSearchCriteria();
		if (request.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(request.getCriteria().getPagination().getLimit());
			detail.setStart(request.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}

		if (request.getCriteria().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(request.getCriteria().getSorting().getSort());
			sortingDetail.setDirection(request.getCriteria().getSorting().getDirection());
			criteria.setSortingDetail(sortingDetail);
		}
		if(request.getCriteria().getKey()!=null)
			criteria.setStaffNameorNo(request.getCriteria().getKey());

		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		
		QueryStaffPageActionResult result=staffService.queryStaffList(criteria, context);
		
		List<OrganicationClient> lt=new ArrayList<OrganicationClient>();
		
		for (Staff staff:result.getResultList()) {
			OrganicationClient client=new OrganicationClient();
			client.setId(staff.getId());
			client.setName(staff.getName());
			client.setType(OrganType.STAFF);
			lt.add(client);
		}
		OrganSearchResult rs=new OrganSearchResult();
		rs.setResult(lt);
		rs.setTotal(result.getTotal());
		return new ChooseOrganizationResponse(rs);
	}
	@Override
	public Class<ChooseOrganizationRequest> getActionType() {
		return ChooseOrganizationRequest.class;
	}

	@Override
	public void rollback(ChooseOrganizationRequest request,
			ChooseOrganizationResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
