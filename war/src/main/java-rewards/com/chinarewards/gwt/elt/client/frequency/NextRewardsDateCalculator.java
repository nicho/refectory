package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;

import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;

/**
 * 
 * @author yanxin
 * @since 0.2.0
 */
public interface NextRewardsDateCalculator {

	/**
	 * Calculate next rewards date from startTime.
	 * 
	 * @param startTime
	 * @param frequency
	 * @return
	 */
	public Date calNextRewardsDate(Date startTime, FrequencyClient frequency);
}
