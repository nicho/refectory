package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

public class FrequencyClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5838796704839724865L;

	/**
	 * 每X年|每X月|每X周|每X日
	 */
	private int interval;

	public FrequencyClient() {
	}

	public FrequencyClient(int interval) {
		this();
		this.setInterval(interval);
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

}
