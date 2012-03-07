package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author lw
 * @since 0.2.0 2011-12-26
 */
public class SearchRewardsItemRequest implements Action<SearchRewardsItemResponse> {

	private RewardsItemCriteria rewardsItem;
	private UserSession userSession;
	public SearchRewardsItemRequest() {
	}

	public SearchRewardsItemRequest(RewardsItemCriteria rewardsItem,UserSession userSession) {
		this.rewardsItem = rewardsItem;
		this.userSession = userSession;
	}

	@Override
	public String toString() {
		return "SearchRewardsItemRequest [rewardsItem=" + rewardsItem + "]";
	}

	// ---- getter ----
	public RewardsItemCriteria getRewardsItem() {
		return rewardsItem;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
