package com.chinarewards.elt.service.reward.rule;

import java.util.List;

import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.user.SysUser;

/**
 * This class provides some useful methods to manipulate {@link Judge}
 * 
 * @author yanxin
 * @since 1.0
 */
public interface JudgeLogic {

	/**
	 * Bind judges to the specified RewardItem.
	 * 
	 * @param caller
	 * @param rewardItemId
	 * @param staffIds
	 */
	public void bindJudgesToRewardItem(SysUser caller, String rewardItemId,
			List<String> staffIds);
	
	/**
	 * Bind judges to the specified RewardItemStore.
	 * 
	 * @param caller
	 * @param rewardItemId
	 * @param staffIds
	 */
	public void bindJudgesToRewardItemStore(SysUser caller, String rewardItemStoreId,
			List<String> staffIds);


	/**
	 * Remove judges from the specified RewardItem.
	 * 
	 * @param rewardItemId
	 */
	public void removeJudgesFromRewardItem(String rewardItemId);
	/**
	 * Remove judges from the specified RewardItemStore.
	 * 
	 * @param rewardItemId
	 */
	public void removeJudgesFromRewardItemStore(String rewardItemStoreId);
	/**
	 * Find list of Judge from the specified RewardItem.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public List<Judge> findJudgesFromRewardItem(String rewardItemId);
	
	/**
	 * Find list of Judge from the specified RewardItemStore.
	 * 
	 * @param rewardItemStoreId
	 * @return
	 */
	public List<Judge> findJudgesFromRewardItemStore(String rewardItemStoreId);

	/**
	 * Find list of Judge from the specified Reward.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public List<Judge> findJudgesFromReward(String rewardId);

	/**
	 * Clone list of Judge from the specified RewardItem to Reward.
	 * 
	 * @param caller
	 * @param fromRewardItemId
	 * @param toRewardId
	 */
	public void cloneJudgesFromRewardItemToReward(SysUser caller,
			String fromRewardItemId, String toRewardId);
/**
 * 复制提名人
 * @param id
 * @param id2
 */
	public void copyJudgeToRewardItem(SysUser caller,String rewardItemStoreId, String rewardItemId);
}
