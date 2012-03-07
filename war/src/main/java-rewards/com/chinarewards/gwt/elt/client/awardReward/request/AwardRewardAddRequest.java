/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardReward.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2011年12月12日 
 */
public class AwardRewardAddRequest implements Action<AwardRewardAddResponse> {

	List<String> staffIds;

	String rewardId;
	String nowUserId;
	
	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	/**
	 * For serialization
	 */
	public AwardRewardAddRequest() {
	}

	public List<String> getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(List<String> staffIds) {
		this.staffIds = staffIds;
	}

	public AwardRewardAddRequest(List<String> staffIds,String rewardId,String nowUserId) {
		this.staffIds=staffIds;
	this.nowUserId=nowUserId;
		this.rewardId=rewardId;
	}

}
