package com.chinarewards.elt.service.reward.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.model.org.RewardsApprovalPolicyEnum;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.google.inject.Inject;

/**
 * The implementation of {@link AwardApprovalDeterminer}
 * 
 * @author yanxin
 * @since 1.0
 */
public class SimpleAwardApprovalDeterminer implements AwardApprovalDeterminer {

	public DepartmentLogic departmentLogic;
	public RewardDao rewardDao;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public SimpleAwardApprovalDeterminer(DepartmentLogic departmentLogic,
			RewardDao rewardDao) {
		this.departmentLogic = departmentLogic;
		this.rewardDao = rewardDao;
	}

	@Override
	public boolean isApprovalRequired(String rewardId) {
		logger.debug(
				"Process in method isApprovalRequired parameter  rewardId:{}",
				new String[] { rewardId });
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		if (reward == null) {
			throw new IllegalArgumentException(
					"can not found anything access assigned rewardsItemId");
		}
		RewardsApprovalPolicyEnum resultEnum = departmentLogic
				.getDepartmentRewardApprovalPolicy(reward.getBuilderDept()
						.getId());
		if (RewardsApprovalPolicyEnum.REQUIRED == resultEnum
				|| RewardsApprovalPolicyEnum.SYSTEM_DEFAULT == resultEnum) {
			return true;
		} else {
			return false;
		}
	}
}
