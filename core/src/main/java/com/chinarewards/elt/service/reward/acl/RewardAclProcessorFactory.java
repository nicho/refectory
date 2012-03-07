package com.chinarewards.elt.service.reward.acl;

import java.util.List;

import com.chinarewards.elt.model.user.UserRole;

/**
 * This factory provides the instance who will deal with Reward or RewardItem
 * aim at a role.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface RewardAclProcessorFactory {

	/**
	 * Provides processor by list of {@link UserRole}.
	 * 
	 * @param userRoles
	 * @return
	 */
	public RewardAclProcessor generateRewardAclProcessor(
			List<UserRole> userRoles);

	/**
	 * Provides processor by array of {@link UserRole}.
	 * 
	 * @param userRoles
	 * @return
	 */
	public RewardAclProcessor generateRewardAclProcessor(UserRole[] userRoles);

}
