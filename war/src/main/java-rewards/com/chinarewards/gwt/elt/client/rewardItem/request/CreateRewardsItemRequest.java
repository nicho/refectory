/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author yanxin
 * @since 0.1.0 2010-12-20
 */
public class CreateRewardsItemRequest implements  Action<CreateRewardsItemResponse> {

	private RewardsItemClient rewardsItem;
	private UserSession userSession;
	private boolean itemStore;
	public CreateRewardsItemRequest() {

	}

	public CreateRewardsItemRequest(RewardsItemClient rewardsItem,UserSession userSession,boolean itemStore) {
		this.rewardsItem = rewardsItem;
		this.userSession = userSession;
		this.itemStore = itemStore;
	}

	public boolean isItemStore() {
		return itemStore;
	}

	public void setItemStore(boolean itemStore) {
		this.itemStore = itemStore;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public RewardsItemClient getRewardsItem() {
		return rewardsItem;
	}

}
