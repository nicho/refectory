/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年1月4日 15:36:46
 */
public class DeleteRewardsRequest implements Action<DeleteRewardsResponse> {

	private String rewardId;
	private String nowUserId;

	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}

	public DeleteRewardsRequest() {
	}

	public DeleteRewardsRequest(String rewardId,String nowUserId) {
		this.rewardId = rewardId;
		this.nowUserId=nowUserId;

	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

}
