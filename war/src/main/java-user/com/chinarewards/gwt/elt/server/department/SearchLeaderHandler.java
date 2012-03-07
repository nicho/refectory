package com.chinarewards.gwt.elt.server.department;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.reward.base.WinnerProcessFlag;
import com.chinarewards.elt.model.vo.WinnersRecordQueryResult;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.department.model.SearchLeaderResult;
import com.chinarewards.gwt.elt.client.department.request.SearchLeaderRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchLeaderResponse;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class SearchLeaderHandler extends	BaseActionHandler<SearchLeaderRequest, SearchLeaderResponse> {

	@InjectLogger
	Logger log;
	IStaffService staffService;

	@Inject
	public SearchLeaderHandler(IStaffService staffService)
	{
		this.staffService=staffService;
	}
	@Override
	public SearchLeaderResponse execute(SearchLeaderRequest request,
			ExecutionContext arg1) throws DispatchException {
		log.debug(
				"Process in method SearchLeaderActionHandler execute. Parameter deptId:{}, phone:{}",
				new String[] { request.getCriteria().getKey(),
						request.getCriteria().getDeptId(), });

		WinnersRecordQueryVo criteria = buildWinnersRecordQueryVo(request);
		String corporationId = request.getUserSession().getCorporationId() ;//企业ID  
		PageStore<WinnersRecordQueryResult> store = staffService.queryWinnerRecords(criteria, corporationId, request.isLimitDataByUserRole());
		SearchLeaderResult result = new SearchLeaderResult();

		log.debug("Total num:{}, size:{}",new Object[] { store.getResultCount(),store.getResultList().size() });
		result.setResult(adapter(store.getResultList()));
		result.setTotal(store.getResultCount());

		return new SearchLeaderResponse(result);
	}

	private WinnersRecordQueryVo buildWinnersRecordQueryVo(	SearchLeaderRequest request) {
				
		boolean isOrgIdsLimit = false;
		List<String> orgIds = new ArrayList<String>();
		if (!request.getCriteria().isChooseAll()) {
			isOrgIdsLimit = true;
			orgIds = request.getCriteria().getOrgIds();
		}
		WinnersRecordQueryVo criteria = new WinnersRecordQueryVo();
		criteria.setFilterRewardsParticipants(isOrgIdsLimit);
		criteria.setOrgIds(orgIds);
		criteria.setKey(request.getCriteria().getKey());
		//criteria.setDeptId(request.getCriteria().getDeptId());
		
		if (request.getCriteria().getDeptId() != null) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(request.getCriteria().getDeptId());
			criteria.setSubDeptIds(list);
			criteria.setIncludeSubDepts(true);	// retain original behavior
		}
		

		if (request.getCriteria().getPagination() != null) {
			PaginationDetail paginationDetail = new PaginationDetail();
			paginationDetail.setStart(request.getCriteria().getPagination()
					.getStart());
			paginationDetail.setLimit(request.getCriteria().getPagination()
					.getLimit());
			criteria.setPaginationDetail(paginationDetail);
		}

		if (request.getCriteria().getSorting() != null) {
			SortingDetail sorting = new SortingDetail();
			sorting.setSort(request.getCriteria().getSorting().getSort());
			sorting.setDirection(request.getCriteria().getSorting()
					.getDirection());
			criteria.setSortingDetail(sorting);
		}

		criteria.setProcessFlag(WinnerProcessFlag.PROCESS_SUCCESS);

		return criteria;
	}

	private List<StaffClient> adapter(List<WinnersRecordQueryResult> records) {
		
		List<StaffClient> list = new ArrayList<StaffClient>();
		System.out.println("dd="+records);
		for (WinnersRecordQueryResult record : records) {
			StaffClient staff = new StaffClient();
			staff.setId(record.getStaffId());
			staff.setName(record.getStaffName());
			staff.setDeptName( record.getDepName());
			staff.setDeptId( record.getDepId());
			
			list.add(staff);
		}

		return list;
	}
	
	protected String buildDepartmentHierarchyName(Department d) {
		Department i = d;
		String s = "";
		while (i != null) {
			if (s.length() > 0) {
				s = " > " + s;
			}
			s = i.getName() + s;
			i = i.getParent();
		}
		return s;
	}

	@Override
	public Class<SearchLeaderRequest> getActionType() {
		return SearchLeaderRequest.class;
	}

	@Override
	public void rollback(SearchLeaderRequest arg0, SearchLeaderResponse arg1,
			ExecutionContext arg2) throws DispatchException {

	}

}
