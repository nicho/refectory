package com.chinarewards.elt.service.reward.rule;

/**
 * The interface used to check if needs rewards verify(审核) when awarding.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface AwardApprovalDeterminer {

	/**
	 * Check whether the specified Reward need to approval.
	 * 
	 * @param rewardId
	 * @return
	 */
	public boolean isApprovalRequired(String rewardId);
}
