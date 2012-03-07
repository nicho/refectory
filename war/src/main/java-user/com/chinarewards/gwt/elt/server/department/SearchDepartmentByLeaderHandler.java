package com.chinarewards.gwt.elt.server.department;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByLeaderRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByLeaderResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the SearchDepartmentByLeaderRequest.
 * 
 * @author yanrui
 */
public class SearchDepartmentByLeaderHandler
		extends
		BaseActionHandler<SearchDepartmentByLeaderRequest, SearchDepartmentByLeaderResponse> {

	@InjectLogger
	Logger logger;

	DepartmentService departmentService;

	@Inject
	public SearchDepartmentByLeaderHandler(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public SearchDepartmentByLeaderResponse execute(
			SearchDepartmentByLeaderRequest request, ExecutionContext response)
			throws DispatchException {
		SearchDepartmentByLeaderResponse searchResponse = new SearchDepartmentByLeaderResponse();
		List<Department> departmentList = departmentService.findDepartmentsManagedByStaffId(request.getLeaderId());
		searchResponse.setDepartmentList(departmentList);

		System.out.println(request.getLeaderId()+"=========leader departmentList:===="+departmentList.size());
		
		return searchResponse;
	}

	@Override
	public Class<SearchDepartmentByLeaderRequest> getActionType() {
		return SearchDepartmentByLeaderRequest.class;
	}

	@Override
	public void rollback(SearchDepartmentByLeaderRequest request,
			SearchDepartmentByLeaderResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
