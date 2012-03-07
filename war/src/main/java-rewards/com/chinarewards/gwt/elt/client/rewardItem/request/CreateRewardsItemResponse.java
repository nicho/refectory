/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after create rewardsItem request.
 * 
 * @author yanxin
 * @since 0.1.0 2010-12-20
 */
public class CreateRewardsItemResponse implements Result {

	public CreateRewardsItemResponse(String id) {
		this.id = id;
	}

	public CreateRewardsItemResponse() {

	}

	private String id;

	public String getId() {
		return id;
	}
}
