package com.chinarewards.elt.service.reward;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.search.RewardGridSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardGridVo;
import com.chinarewards.elt.model.user.UserContext;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * The implementation of {@link RewardService}
 * 
 * @author yanrui
 * @since 1.5
 */
@Transactional
public class RewardGridServiceImpl implements RewardGridService {
	private final RewardGridLogic rewardGridLogic;

	@Inject
	public RewardGridServiceImpl(RewardGridLogic rewardGridLogic) {
		this.rewardGridLogic = rewardGridLogic;

	}

	@Override
	public PageStore<RewardGridVo> fetchRewards_STAFF(UserContext context,
			RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewards_STAFF(context, criteria);
	}

	@Override
	public PageStore<RewardGridVo> fetchRewards_ALL(UserContext context,
			RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewards_ALL(context, criteria);
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF(UserContext context,
			RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewardsItem_STAFF(context, criteria);
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_ALL(UserContext context,
			RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewardsItem_ALL(context, criteria);
	}

	@Override
	public PageStore<RewardGridVo> fetchRewards_STAFF_GETED(UserContext context,
			RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewards_STAFF_GETED(context, criteria);
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_PARTAKE(
			UserContext context, RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewardsItem_STAFF_PARTAKE(context,criteria);
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_RUSH(UserContext context,
			RewardGridSearchVo criteria) {
		return rewardGridLogic.fetchRewardsItem_STAFF_RUSH(context,criteria);
	}
	
	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_COMPANY_OTHER(UserContext context,
			RewardGridSearchVo criteria){
		return rewardGridLogic.fetchRewardsItem_COMPANY_OTHER(context, criteria);
	}

}
