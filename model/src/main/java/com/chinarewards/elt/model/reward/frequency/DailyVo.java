/**
 * 
 */
package com.chinarewards.elt.model.reward.frequency;

/**
 * 
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public class DailyVo extends AbstractRewardsFrequency implements Daily {

	/**
	 * 
	 */
	public DailyVo() {
		super();
	}

	/**
	 * 
	 * 
	 * @param interval
	 */
	public DailyVo(int interval) {
		super(interval);
	}

	@Override
	public String toString() {
		return "interval=" + interval;
	}

}
