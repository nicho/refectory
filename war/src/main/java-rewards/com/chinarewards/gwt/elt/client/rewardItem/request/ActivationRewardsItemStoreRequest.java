/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2011年12月26日 10:37:22
 */
public class ActivationRewardsItemStoreRequest implements
		Action<ActivationRewardsItemStroeResponse> {

	private String rewardsItemStoreId;
	private String nowUserId;
	

	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}



	public String getRewardsItemStoreId() {
		return rewardsItemStoreId;
	}

	public void setRewardsItemStoreId(String rewardsItemStoreId) {
		this.rewardsItemStoreId = rewardsItemStoreId;
	}

	public ActivationRewardsItemStoreRequest(String rewardsItemStoreId,String nowUserId) {
		this.rewardsItemStoreId=rewardsItemStoreId;
		this.nowUserId=nowUserId;

	}
	public ActivationRewardsItemStoreRequest() {


	}
}
