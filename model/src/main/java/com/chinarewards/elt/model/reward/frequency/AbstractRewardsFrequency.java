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
public abstract class AbstractRewardsFrequency implements RewardsFrequency {

	int interval;

	public AbstractRewardsFrequency() {
	}

	public AbstractRewardsFrequency(int interval) {
		this.interval = interval;
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval
	 *            the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

}
