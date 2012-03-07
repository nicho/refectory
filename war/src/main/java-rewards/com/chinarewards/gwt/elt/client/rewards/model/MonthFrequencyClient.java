package com.chinarewards.gwt.elt.client.rewards.model;

public class MonthFrequencyClient extends FrequencyClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8827441180680691665L;

	/**
	 * 1号到28或29或30或31号
	 */
	private Integer monthDay;

	public MonthFrequencyClient() {
	}

	public MonthFrequencyClient(int interval, Integer monthDay) {
		super(interval);
		this.monthDay = monthDay;
	}

	public Integer getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(Integer monthDay) {
		this.monthDay = monthDay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "interval=" +  getInterval() + ", monthDay=" + monthDay;
	}

}
