package com.chinarewards.gwt.elt.server.hrbox;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardService;
import com.chinarewards.gwt.elt.client.hrbox.request.HrBoxRewardsRequest;
import com.chinarewards.gwt.elt.client.hrbox.request.HrBoxRewardsResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 0.2.0 2012-2-27
 */
public class HrBoxRewardsHandler extends
		BaseActionHandler<HrBoxRewardsRequest, HrBoxRewardsResponse> {

	@InjectLogger
	Logger logger;

	RewardService rewardService;

	@Inject
	public HrBoxRewardsHandler(RewardService rewardService) {
		this.rewardService = rewardService;

	}

	@Override
	public HrBoxRewardsResponse execute(HrBoxRewardsRequest request,
			ExecutionContext context) throws DispatchException {

		HrBoxRewardsResponse resp = new HrBoxRewardsResponse();
		RewardSearchVo criteria = new RewardSearchVo();
		RewardsCriteria rewards =   request.getRewards();
		criteria.setStatus(RewardStatus.valueOf(rewards.getStatus().toString()));
		criteria.setLastMonth(rewards.getLastMonth());
		UserContext uc=new UserContext();
		uc.setCorporationId(request.getSession().getCorporationId());
		uc.setUserId(request.getSession().getToken());
		uc.setLoginName(request.getSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		List<RewardVo> rewardsList = rewardService.getRewardsByHrBox(uc, criteria);
		if(rewardsList!=null)
		{
		   resp.setResult(adapterToClient(rewardsList));
		}
		return resp;
	}

	//从服务端得到的数据到客户端在列表显示的数据
	public static List<RewardsClient> adapterToClient(List<RewardVo> service) {
			List<RewardsClient> resultList = new ArrayList<RewardsClient>();

				for (RewardVo item : service) {
					RewardsClient client = new RewardsClient();
					client.setId(item.getId());
					client.setName(item.getName());
					resultList.add(client);
				}

				return resultList;
			}

	@Override
	public Class<HrBoxRewardsRequest> getActionType() {
		return HrBoxRewardsRequest.class;
	}

	@Override
	public void rollback(HrBoxRewardsRequest req, HrBoxRewardsResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
