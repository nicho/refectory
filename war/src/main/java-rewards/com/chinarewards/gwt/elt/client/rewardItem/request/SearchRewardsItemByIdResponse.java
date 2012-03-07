/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;

/**
 * @author lw
 * @since 0.2.0 2011-12-25
 */
public class SearchRewardsItemByIdResponse implements Result {

	private RewardsItemClient rewardsItem;
   
	public SearchRewardsItemByIdResponse() {

	}
	
	public SearchRewardsItemByIdResponse(RewardsItemClient rewardsItem) {
		this.rewardsItem = rewardsItem;
		
	}

	
	public RewardsItemClient getRewardsItem() {
		return rewardsItem;
	}

	public void setRewardsItem(RewardsItemClient rewardsItem) {
		this.rewardsItem = rewardsItem;
	}

}
