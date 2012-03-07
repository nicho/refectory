package com.chinarewards.gwt.elt.client.rewardItem.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemStaffClient;

/**
 * @author yanrui
 */
public class SearchStaffRewardsItemResponse implements Result {

	private List<RewardsItemStaffClient> result;
	private int total;

	/**
	 * @return the result
	 */
	public List<RewardsItemStaffClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<RewardsItemStaffClient> result) {
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
