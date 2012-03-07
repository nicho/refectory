package com.chinarewards.gwt.elt.server.rewardItem;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchOrganizationRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchOrganizationResponse;
import com.chinarewards.gwt.elt.client.rewards.model.StaffOrDepartmentAC;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class SearchOrganizationHandler extends	BaseActionHandler<SearchOrganizationRequest, SearchOrganizationResponse> {

	@InjectLogger
	Logger logger;
	
	RewardItemService rewardItemService;
	/**
	 * 限制的条数
	 */
	private int limit = 10;
	@Inject
	public SearchOrganizationHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	@Override
	public Class<SearchOrganizationRequest> getActionType() {
		return SearchOrganizationRequest.class;
	}

	@Override
	public SearchOrganizationResponse execute(SearchOrganizationRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("SearchOrganizationHandler execute!!");
		String key = action.getKey();
		String corporationId = action.getCorporationId();
	//	RewardsItemService rewardsItemService = ServiceLocatorUtil.getServiceLocator().getRewardsItemService();
		
		List<StaffAndDeptmentAutoCompile> list = rewardItemService.staffAndDeptmentAutoCompile(corporationId,key, limit);
		System.out.println("The result list size:" + list.size());
		SearchOrganizationResponse response = new SearchOrganizationResponse(adapter(list));
		return response;
	}

	private List<StaffOrDepartmentAC> adapter(
			List<StaffAndDeptmentAutoCompile> list) {
		List<StaffOrDepartmentAC> result = new ArrayList<StaffOrDepartmentAC>();
		for (StaffAndDeptmentAutoCompile com : list) {
			result.add(new StaffOrDepartmentAC(com.getId(), com
					.getChooseItemShowName(), com.getAutoCompileName()));
		}

		return result;
	}

	@Override
	public void rollback(SearchOrganizationRequest action,
			SearchOrganizationResponse result, ExecutionContext context)
			throws DispatchException {
	}

}
