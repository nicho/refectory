package com.chinarewards.gwt.elt.client.rewards.model;

public class UnifiedAmountRuleClient extends RewardsAmountRuleClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4381440064269352915L;

	private Double rewardsFrom;
	private Double rewardsTo;

	public UnifiedAmountRuleClient() {

	}

	public UnifiedAmountRuleClient(Double rewardsFrom, Double rewardsTo) {
		this.rewardsFrom = rewardsFrom;
		this.rewardsTo = rewardsTo;
	}

	public Double getRewardsFrom() {
		return rewardsFrom;
	}

	public void setRewardsFrom(Double rewardsFrom) {
		this.rewardsFrom = rewardsFrom;
	}

	public Double getRewardsTo() {
		return rewardsTo;
	}

	public void setRewardsTo(Double rewardsTo) {
		this.rewardsTo = rewardsTo;
	}

}
