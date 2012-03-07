package com.chinarewards.elt.service.reward.frequency;

import java.util.Date;

import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.DateRangeModel;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;

/**
 * It provides some useful methods to manipulate {@link Frequency}
 * 
 * @author yanxin
 * @since 1.0
 */
public interface FrequencyLogic {

	/**
	 * Remove frequency from a specified {@link RewardItem}.
	 * 
	 * @param rewardItemId
	 */
	public void removeFrequencyFromRewardItem(String rewardItemId);
	
	/**
	 * Remove frequency from a specified {@link RewardItemStore}.
	 * 
	 * @param rewardItemStoreId
	 */
	public void removeFrequencyFromRewardItemStore(String rewardItemStoreId);

	/**
	 * Bind a frequency to a specified {@link RewardItem}.
	 * 
	 * @param rewardItemId
	 * @param frequency
	 */
	public void bindFrequencyToRewardItem(SysUser caller, String rewardItemId,
			RewardsFrequency frequency);
	
	/**
	 * Bind a frequency to a specified {@link RewardItem}.
	 * 
	 * @param rewardItemStoreId
	 * @param frequency
	 */
	public void bindFrequencyToRewardItemStore(SysUser caller, String rewardItemStoreId,
			RewardsFrequency frequency);

	/**
	 * Get list of frequency about the specified RewardItem. All the frequency
	 * must be complete. e.g. WeekFrequency must contains all the
	 * WeekFrequencyDay data.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public Frequency getFrequencyOfRewardItem(String rewardItemId);
	
	/**
	 * Get list of frequency about the specified RewardItem. All the frequency
	 * must be complete. e.g. WeekFrequency must contains all the
	 * WeekFrequencyDay data.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public Frequency getFrequencyOfRewardItemStore(String rewardItemStoreId);

	/**
	 * Calculate the date range from the specified frequency.
	 * 
	 * @param frequency
	 * @param currTime
	 * @return
	 */
	public DateRangeModel calDateRangeFromFrequency(Frequency frequency,
			Date currTime);

	/**
	 * Calculate the reward name from the frequency and the specified name.
	 * 
	 * @param name
	 * @param frequency
	 * @param currTime
	 * @return
	 */
	public String calRewardNameFromFrequency(String name, Frequency frequency,
			Date currTime);

	/**
	 * Calculate next award time.
	 * 
	 * @param frequency
	 * @param lastRunTime
	 * @return
	 */
	public Date calNextAwardTime(Frequency frequency, Date lastRunTime);

	public Frequency copyFrequencyToRewardItem(SysUser caller,String rewardItemStoreId, String rewardItemId);
}
