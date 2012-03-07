package com.chinarewards.gwt.elt.server.department;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.org.search.DepartmentListVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.gwt.elt.client.department.model.DepartmentCriteria;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchDepartmentHandler extends
		BaseActionHandler<SearchDepartmentRequest, SearchDepartmentResponse> {

	@InjectLogger
	Logger logger;

	DepartmentService departmentService;

	@Inject
	public SearchDepartmentHandler(DepartmentService departmentService) {
		this.departmentService = departmentService;

	}

	@Override
	public SearchDepartmentResponse execute(SearchDepartmentRequest request,
			ExecutionContext context) throws DispatchException {

		SearchDepartmentResponse resp = new SearchDepartmentResponse();

		DepartmentCriteria department = request.getDepartment();
		DepartmentListVo criteria = adapter(department);
		PageStore<DepartmentListVo> departmentPage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getCorporationId());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserRoles()));
		uc.setUserId(request.getUserId());

//		departmentPage = departmentService.departmentList(uc, criteria);
//		resp.setTotal(departmentPage.getResultCount());
//		resp.setResult(DepartmentAdapter.adapter(departmentPage.getResultList()));

		return resp;
	}

	private DepartmentListVo adapter(DepartmentCriteria criteria) {
		DepartmentListVo vo = new DepartmentListVo();
		if (criteria.getName() != null) {
			vo.setName(criteria.getName());
		}
		if (criteria.getStatus() != null) {
//			vo.setStatus(DepartmentStatus.valueOf(criteria.getStatus().toString()));
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

	@Override
	public Class<SearchDepartmentRequest> getActionType() {
		return SearchDepartmentRequest.class;
	}

	@Override
	public void rollback(SearchDepartmentRequest req, SearchDepartmentResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
