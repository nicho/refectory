package com.chinarewards.gwt.elt.server.chooseStaff;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.service.reward.nominee.NomineeService;
import com.chinarewards.gwt.elt.client.chooseStaff.request.SearchStaffChooseRequest;
import com.chinarewards.gwt.elt.client.chooseStaff.request.SearchStaffChooseResponse;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffSearchResult;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class SearchStaffActionHandler extends
		BaseActionHandler<SearchStaffChooseRequest, SearchStaffChooseResponse> {

	@InjectLogger
	Logger log;

	NomineeService nomineeService;

	@Inject
	public SearchStaffActionHandler(NomineeService nomineeService) {
		this.nomineeService = nomineeService;
	}

	@Override
	public SearchStaffChooseResponse execute(SearchStaffChooseRequest request,
			ExecutionContext arg1) throws DispatchException {
		log.debug(
				"Process in method SearchStaffActionHandler execute. Parameter deptId:{}, phone:{}",
				new String[] { request.getCriteria().getKey(),
						request.getCriteria().getDeptId(), });

		WinnersRecordQueryVo criteria = buildWinnersRecordQueryVo(request);

		PageStore<Candidate> store = nomineeService
				.getCandidatesFromRewardAndQueryVo(request.getCriteria()
						.getRewardId(), criteria);

		StaffSearchResult result = new StaffSearchResult();

		log.debug("Total num:{}, size:{}",
				new Object[] { store.getResultCount(),
						store.getResultList().size() });
		result.setResult(adapter(store.getResultList()));
		result.setTotal(store.getResultCount());

		return new SearchStaffChooseResponse(result);
	}

	private WinnersRecordQueryVo buildWinnersRecordQueryVo(
			SearchStaffChooseRequest request) {

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
		// criteria.setDeptId(request.getCriteria().getDeptId());

		if (request.getCriteria().getDeptId() != null) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(request.getCriteria().getDeptId());
			criteria.setSubDeptIds(list);
			criteria.setIncludeSubDepts(true); // retain original behavior
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

		// criteria.setProcessFlag(WinnerProcessFlag.PROCESS_SUCCESS);

		return criteria;
	}

	private List<StaffClient> adapter(List<Candidate> records) {

		List<StaffClient> list = new ArrayList<StaffClient>();
		for (Candidate record : records) {
			StaffClient staff = new StaffClient();
			staff.setId(record.getStaff().getId());
			staff.setName(record.getStaff().getName());
			staff.setNominateCount(record.getNominatecount());
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
	public Class<SearchStaffChooseRequest> getActionType() {
		return SearchStaffChooseRequest.class;
	}

	@Override
	public void rollback(SearchStaffChooseRequest arg0,
			SearchStaffChooseResponse arg1, ExecutionContext arg2)
			throws DispatchException {

	}

}
