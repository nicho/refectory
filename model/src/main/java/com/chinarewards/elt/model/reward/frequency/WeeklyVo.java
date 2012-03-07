/**
 * 
 */
package com.chinarewards.elt.model.reward.frequency;

import java.util.Arrays;

/**
 * 
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public class WeeklyVo extends AbstractRewardsFrequency implements Weekly {

	private WeekDays[] weekdays;

	/**
	 * 
	 */
	public WeeklyVo() {
		super();
	}

	/**
	 * 
	 * 
	 * @param interval
	 * @param day
	 */
	public WeeklyVo(int interval, WeekDays[] weekdays) {
		super(interval);
		this.weekdays = weekdays;
	}

	@Override
	public WeekDays[] getWeekdays() {
		return weekdays;
	}

	/**
	 * @param weekdays
	 *            the weekdays to set
	 */
	public void setWeekdays(WeekDays[] weekdays) {
		this.weekdays = weekdays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WeeklyVo [weekdays=" + Arrays.toString(weekdays)
				+ ", interval=" + interval + "]";
	}

}
