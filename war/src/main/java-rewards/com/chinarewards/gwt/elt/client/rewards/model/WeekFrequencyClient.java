package com.chinarewards.gwt.elt.client.rewards.model;

import java.util.ArrayList;
import java.util.List;

public class WeekFrequencyClient extends FrequencyClient {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3651375088901702631L;

	private List<Integer> weekDays = new ArrayList<Integer>();

	public WeekFrequencyClient() {
	}

	public WeekFrequencyClient(int interval, List<Integer> weekDays) {
		super(interval);
		if (weekDays != null) {
			this.weekDays.addAll(weekDays);
		}
	}

	/**
	 * 星期一到星期日(1-7)
	 */
	public List<Integer> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(List<Integer> weekDays) {
		this.weekDays = weekDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "weekDays=" + weekDays + ", interval=" + getInterval();
	}

}
