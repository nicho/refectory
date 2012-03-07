package com.chinarewards.gwt.elt.server.staff;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.staff.StaffWinSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinVo;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.staffView.model.StaffWinClient;
import com.chinarewards.gwt.elt.client.staffView.request.StaffWinRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffWinResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the StaffWinRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class SearchStaffWinActionHandler extends
		BaseActionHandler<StaffWinRequest, StaffWinResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public SearchStaffWinActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public StaffWinResponse execute(StaffWinRequest request,
			ExecutionContext response) throws DispatchException {

		StaffWinResponse staffResponse = new StaffWinResponse();
		StaffWinSearchCriteria criteria=new StaffWinSearchCriteria();
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
		
		criteria.setStaffId(request.getCriteria().getStaffId());

		
		StaffWinVo result=staffService.findStaffWinReward(criteria);
		
		List<StaffWinClient> lt=new ArrayList<StaffWinClient>();
		for (Winner win:result.getResultList()) {
			StaffWinClient client=new StaffWinClient();
			client.setRewardName(win.getReward().getName());
			client.setWinTime(win.getWinTime());
			client.setPresentedName(win.getCreatedBy().getStaff().getName());
			lt.add(client);
		}
		staffResponse.setResult(lt);
		staffResponse.setTotal(result.getTotal());
		
		return staffResponse;
	}

	@Override
	public Class<StaffWinRequest> getActionType() {
		return StaffWinRequest.class;
	}

	@Override
	public void rollback(StaffWinRequest request,
			StaffWinResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
