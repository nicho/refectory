package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

public class RewardsAmountRuleClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5895183479915993598L;

	private String id;

	private String name;

	public RewardsAmountRuleClient() {

	}

	public RewardsAmountRuleClient(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
