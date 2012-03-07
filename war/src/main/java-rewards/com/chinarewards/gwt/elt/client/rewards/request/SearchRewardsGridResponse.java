package com.chinarewards.gwt.elt.client.rewards.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;

/**
 * @author yanrui
 */
public class SearchRewardsGridResponse implements Result {

	private List<RewardsGridClient> result;
	private int total;

	/**
	 * @return the result
	 */
	public List<RewardsGridClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<RewardsGridClient> result) {
		this.result = result;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
