/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author nicho
 * @since 2012年1月4日 15:37:56
 */
public class DeleteRewardsResponse implements Result {

	private String rewardId;
	public DeleteRewardsResponse() {

	}
	public DeleteRewardsResponse(String rewardId) {
		this.rewardId = rewardId;
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

}
