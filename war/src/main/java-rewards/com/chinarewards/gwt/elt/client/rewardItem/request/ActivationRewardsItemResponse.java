/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author nicho
 * @since 2011年12月26日 10:37:22
 */
public class ActivationRewardsItemResponse implements Result {

	public ActivationRewardsItemResponse(String name) {
		this.name = name;
	}

	public ActivationRewardsItemResponse() {

	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
