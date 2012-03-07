package com.chinarewards.gwt.elt.server.staff;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.staff.StaffSearchCriteria;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListRequest;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the SearchStaffListRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class SearchStaffListActionHandler extends
		BaseActionHandler<SearchStaffListRequest, SearchStaffListResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public SearchStaffListActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public SearchStaffListResponse execute(SearchStaffListRequest request,
			ExecutionContext response) throws DispatchException {

		SearchStaffListResponse staffResponse = new SearchStaffListResponse();
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
		if(request.getCriteria().getStaffNameorNo()!=null)
			criteria.setStaffNameorNo(request.getCriteria().getStaffNameorNo());
		if(request.getCriteria().getStaffStatus()!=null)
			criteria.setStaffStatus(com.chinarewards.elt.model.staff.StaffStatus.valueOf(request.getCriteria().getStaffStatus().toString()));
		if(request.getCriteria().getStaffRole()!=null)
			criteria.setStaffRole(UserRole.valueOf(request.getCriteria().getStaffRole().toString()));
		if(request.getCriteria().isColleaguePage()==true)
			criteria.setColleaguePage(request.getCriteria().isColleaguePage());
		
		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		
		QueryStaffPageActionResult result=staffService.queryStaffList(criteria, context);
		
		List<StaffListClient> lt=new ArrayList<StaffListClient>();
		for (Staff staff:result.getResultList()) {
			StaffListClient client=new StaffListClient();
			client.setStaffId(staff.getId());
			client.setStaffNo(staff.getJobNo());
			client.setStaffName(staff.getName());
			client.setPhoto(staff.getPhoto());
			client.setPhone(staff.getPhone());
			client.setEmail(staff.getEmail());
			client.setJobPosition(staff.getJobPosition());
			if(staff.getDepartment()!=null)
			client.setDepartmentName(staff.getDepartment().getName());
			if(staff.getStatus()!=null)
			client.setStaffStatus(StaffStatus.valueOf(staff.getStatus().toString()));
			
			lt.add(client);
		}
		staffResponse.setResult(lt);
		staffResponse.setTotal(result.getTotal());
		
		return staffResponse;
	}

	@Override
	public Class<SearchStaffListRequest> getActionType() {
		return SearchStaffListRequest.class;
	}

	@Override
	public void rollback(SearchStaffListRequest request,
			SearchStaffListResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
