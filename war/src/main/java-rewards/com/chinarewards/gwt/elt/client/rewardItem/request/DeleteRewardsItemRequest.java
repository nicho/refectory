/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2011年12月26日 10:37:22
 */
public class DeleteRewardsItemRequest implements
		Action<DeleteRewardsItemResponse> {

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	private String rewardsItemId;
	
	private boolean isItemStore ;
	private UserSession userSession;
	

	public String getRewardsItemId() {
		return rewardsItemId;
	}

	public void setRewardsItemId(String rewardsItemId) {
		this.rewardsItemId = rewardsItemId;
	}

	public DeleteRewardsItemRequest(String rewardsItemId,boolean isItemStore,UserSession userSession) {
		this.rewardsItemId=rewardsItemId;
		this.isItemStore = isItemStore;
        this.userSession= userSession;
	}
	public boolean isItemStore() {
		return isItemStore;
	}

	public void setItemStore(boolean isItemStore) {
		this.isItemStore = isItemStore;
	}

	public DeleteRewardsItemRequest() {


	}
}
