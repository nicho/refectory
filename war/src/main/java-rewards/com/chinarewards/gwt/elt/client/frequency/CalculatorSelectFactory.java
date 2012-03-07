package com.chinarewards.gwt.elt.client.frequency;

/**
 * 
 * @author yanxin
 * @since 0.2.0
 */
public class CalculatorSelectFactory {

	public NextRewardsDateCalculator getCalculator(boolean isRewardedBefore) {
		if (isRewardedBefore) {
			return new NextRewardsDateCalculatorRewardedBefore();
		} else {
			return new NextRewardsDateCalculatorNoRewardedBefore();
		}
	}
	public NextRewardsDateCalculator getCalculator(){
		return new StartRewardsDateCalculator();
	}
}
