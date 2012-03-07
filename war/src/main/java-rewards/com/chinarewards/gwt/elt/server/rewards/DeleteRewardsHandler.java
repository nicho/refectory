package com.chinarewards.gwt.elt.server.rewards;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardService;
import com.chinarewards.gwt.elt.client.rewards.request.DeleteRewardsRequest;
import com.chinarewards.gwt.elt.client.rewards.request.DeleteRewardsResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author Cream
 * @since 0.2.0 2010-12-27
 */
public class DeleteRewardsHandler extends
		BaseActionHandler<DeleteRewardsRequest, DeleteRewardsResponse> {

	@InjectLogger
	Logger logger;

	RewardService rewardService;

	@Inject
	public DeleteRewardsHandler(RewardService rewardService) {
		this.rewardService = rewardService;

	}

	@Override
	public DeleteRewardsResponse execute(DeleteRewardsRequest request,
			ExecutionContext context) throws DispatchException {

		UserContext uc = new UserContext();
		uc.setUserId(request.getNowUserId());

		String rewardid = rewardService.deleteReward(request.getRewardId(), uc);

		return new DeleteRewardsResponse(rewardid);
	}

	@Override
	public Class<DeleteRewardsRequest> getActionType() {
		return DeleteRewardsRequest.class;
	}

	@Override
	public void rollback(DeleteRewardsRequest req, DeleteRewardsResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
