/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;

/**
 * @author lw
 * @since 0.2.0 2011-12-26
 */
public class SearchRewardsItemResponse implements Result {

	private List<RewardsItemClient> result;
	private int total;

	/**
	 * @return the result
	 */
	public List<RewardsItemClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<RewardsItemClient> result) {
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
