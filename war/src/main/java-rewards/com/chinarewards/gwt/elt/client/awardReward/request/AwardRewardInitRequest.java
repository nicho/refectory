/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardReward.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2011年12月12日 
 */
public class AwardRewardInitRequest implements Action<AwardRewardInitResponse> {

	private String awardsId;//奖项ID

	public String getAwardsId() {
		return awardsId;
	}

	public void setAwardsId(String awardsId) {
		this.awardsId = awardsId;
	}

	
	/**
	 * For serialization
	 */
	public AwardRewardInitRequest() {
	}

	public AwardRewardInitRequest(String awardsId) {
		this.awardsId=awardsId;
	}
}
