/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author nicho
 * @since 2011年12月26日 10:37:22
 */
public class DeleteRewardsItemResponse implements Result {

	public DeleteRewardsItemResponse(String name) {
		this.name = name;
	}

	public DeleteRewardsItemResponse() {

	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
