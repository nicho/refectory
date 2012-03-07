/**
 * 
 */
package com.chinarewards.elt.model.reward.frequency;

/**
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public interface Yearly extends RewardsFrequency {

	/**
	 * 
	 * @return
	 */
	public int getInterval();

	/**
	 * Returns the month of year. 1 = January, 2 = February, etc.
	 * 
	 * @return
	 */
	public Integer getMonth();

	/**
	 * Day of month. 1 = day one. 1 based.
	 * 
	 * @return
	 */
	public Integer getDay();

}
