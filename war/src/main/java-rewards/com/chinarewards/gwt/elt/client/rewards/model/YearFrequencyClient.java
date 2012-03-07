package com.chinarewards.gwt.elt.client.rewards.model;

public class YearFrequencyClient extends FrequencyClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2199285270782974975L;

	/**
	 * 1月---12月
	 */
	private int yearMonth;

	/**
	 * 1号---28,29,30,31号
	 */
	private int yearMonthDay;

	public YearFrequencyClient() {
	}

	/**
	 * 
	 * @param interval
	 * @param month
	 *            1 to 12
	 * @param day
	 *            1 to 31
	 */
	public YearFrequencyClient(int interval, int month, int day) {
		super(interval);
		this.yearMonth = month;
		this.yearMonthDay = day;
	}

	public int getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getYearMonthDay() {
		return yearMonthDay;
	}

	public void setYearMonthDay(int yearMonthDay) {
		this.yearMonthDay = yearMonthDay;
	}

	@Override
	public String toString() {
		return "yearMonth=" + yearMonth
				+ ", yearMonthDay=" + yearMonthDay + ", getInterval()="
				+ getInterval();
	}

}
