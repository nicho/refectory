package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;

public class NomineeRecordsCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169735510780604489L;

	private String rewardsId;

	private PaginationDetailClient pagination;

	public String getRewardsId() {
		return rewardsId;
	}

	public void setRewardsId(String rewardsId) {
		this.rewardsId = rewardsId;
	}

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

}
