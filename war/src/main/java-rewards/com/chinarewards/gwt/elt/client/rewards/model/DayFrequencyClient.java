package com.chinarewards.gwt.elt.client.rewards.model;

/**
 * 
 * 
 * @author yanxin
 * 
 */
public class DayFrequencyClient extends FrequencyClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6548625324202104317L;

	public DayFrequencyClient() {
	}

	public DayFrequencyClient(int interval) {
		super(interval);
	}

	@Override
	public String toString() {
		return "interval=" + getInterval();
	}

}
