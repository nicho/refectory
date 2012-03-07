package com.chinarewards.gwt.elt.server.rewardItem;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemStoreRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemStroeResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class ActivationRewardsItemStroeHandler extends	BaseActionHandler<ActivationRewardsItemStoreRequest, ActivationRewardsItemStroeResponse> {

	@InjectLogger
	Logger logger;
	RewardItemService rewardItemService;

	@Inject
	public ActivationRewardsItemStroeHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	@Override
	public Class<ActivationRewardsItemStoreRequest> getActionType() {
		return ActivationRewardsItemStoreRequest.class;
	}

	@Override
	public ActivationRewardsItemStroeResponse execute(ActivationRewardsItemStoreRequest action,
			ExecutionContext context) throws DispatchException {
		UserContext uc=new UserContext();
		uc.setUserId(action.getNowUserId());
		String name=rewardItemService.copyRewardItenStoreToRewardItem(uc, action.getRewardsItemStoreId());

		return new ActivationRewardsItemStroeResponse(name);
	}



	@Override
	public void rollback(ActivationRewardsItemStoreRequest action,
			ActivationRewardsItemStroeResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
