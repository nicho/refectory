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
public class YearlyVo extends AbstractRewardsFrequency implements Yearly {

	private Integer day;

	private Integer month;

	/**
	 * 
	 */
	public YearlyVo() {
		super();
	}

	/**
	 * 
	 * 
	 * @param interval
	 * @param day
	 */
	public YearlyVo(int interval, Integer month) {
		super(interval);
		this.month = month;
	}

	/**
	 * 
	 * 
	 * @param interval
	 * @param day
	 */
	public YearlyVo(int interval, Integer month, Integer day) {
		super(interval);
		this.month = month;
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
	public YearlyVo(int interval) {
		super(interval);
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "day=" + day + ", month=" + month;
	}

}
