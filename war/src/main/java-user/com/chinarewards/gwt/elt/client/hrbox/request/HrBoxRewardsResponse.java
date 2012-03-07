/**
 * 
 */
package com.chinarewards.gwt.elt.client.hrbox.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;

/**
 * @author Cream
 * @since 0.2.0 2010-12-27
 */
public class HrBoxRewardsResponse implements Result {

	private List<RewardsClient> result;
	private int total;

	/**
	 * @return the result
	 */
	public List<RewardsClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<RewardsClient> result) {
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
