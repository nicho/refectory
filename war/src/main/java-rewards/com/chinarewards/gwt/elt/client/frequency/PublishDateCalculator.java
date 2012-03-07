package com.chinarewards.gwt.elt.client.frequency;

import java.util.Date;

import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;



/**
 * 
 * @author yanxin
 * @since 0.2.0
 */
public interface PublishDateCalculator {

	public Date calPublishDateFromRewardsDate(Date rewardsDate,
			FrequencyClient frequency);
}
