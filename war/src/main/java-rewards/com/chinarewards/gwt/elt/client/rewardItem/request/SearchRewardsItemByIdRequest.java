/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author lw
 * @since 0.2.0 2011-12-27
 */
public class SearchRewardsItemByIdRequest implements
		Action<SearchRewardsItemByIdResponse> {

	private String id;
	private boolean isItemStore;//是奖项还是奖项库
	public SearchRewardsItemByIdRequest() {
	}

	public SearchRewardsItemByIdRequest(String id,boolean isItemStore) {
		this.id = id;
		this.isItemStore = isItemStore;
	}

	public boolean isItemStore() {
		return isItemStore;
	}

	public void setItemStore(boolean isItemStore) {
		this.isItemStore = isItemStore;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
