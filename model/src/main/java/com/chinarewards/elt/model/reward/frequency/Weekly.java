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
public interface Weekly extends RewardsFrequency {

	/**
	 * 
	 * 
	 * @return
	 */
	public int getInterval();

	/**
	 * Returns the list of weekdays.
	 * 
	 * @return
	 */
	public WeekDays[] getWeekdays();
	
}
