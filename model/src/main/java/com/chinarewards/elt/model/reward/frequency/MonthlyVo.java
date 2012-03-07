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
public class MonthlyVo extends AbstractRewardsFrequency implements Monthly {

	private Integer day;

	/**
	 * 
	 */
	public MonthlyVo() {
		super();
	}

	/**
	 * 
	 * 
	 * @param interval
	 * @param day
	 */
	public MonthlyVo(int interval, Integer day) {
		super(interval);
		this.day = day;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * 
	 * 
	 * @param interval
	 */
	public MonthlyVo(int interval) {
		super(interval);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "day=" + day + ", interval=" + interval;
	}

}
