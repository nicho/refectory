package com.chinarewards.gwt.elt.server.rewardItem;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class ActivationRewardsItemHandler extends	BaseActionHandler<ActivationRewardsItemRequest, ActivationRewardsItemResponse> {

	@InjectLogger
	Logger logger;
	RewardItemService rewardItemService;

	@Inject
	public ActivationRewardsItemHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	@Override
	public Class<ActivationRewardsItemRequest> getActionType() {
		return ActivationRewardsItemRequest.class;
	}

	@Override
	public ActivationRewardsItemResponse execute(ActivationRewardsItemRequest action,
			ExecutionContext context) throws DispatchException {
		UserContext uc=new UserContext();
		uc.setUserId(action.getNowUserId());
		String name="";
		if("activation".equals(action.getFal()))
			 name=rewardItemService.enableRewardItem(uc, action.getRewardsItemId());
		if("freeze".equals(action.getFal()))
			 name=rewardItemService.disableRewardItem(uc, action.getRewardsItemId());
		return new ActivationRewardsItemResponse(name);
	}



	@Override
	public void rollback(ActivationRewardsItemRequest action,
			ActivationRewardsItemResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
