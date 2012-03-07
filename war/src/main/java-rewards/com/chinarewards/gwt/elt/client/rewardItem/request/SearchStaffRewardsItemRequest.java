package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemStaffCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author yanrui
 */
public class SearchStaffRewardsItemRequest implements
		Action<SearchStaffRewardsItemResponse> {

	private RewardsItemStaffCriteria rewardsItem;
	private UserSession userSession;

	public SearchStaffRewardsItemRequest() {
	}

	public SearchStaffRewardsItemRequest(RewardsItemStaffCriteria rewardsItem,
			UserSession userSession) {
		this.rewardsItem = rewardsItem;
		this.userSession = userSession;
	}

	@Override
	public String toString() {
		return "SearchRewardsItemRequest [rewardsItem=" + rewardsItem + "]";
	}

	// ---- getter ----
	public RewardsItemStaffCriteria getRewardsItemStaffCriteria() {
		return rewardsItem;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
