package com.chinarewards.gwt.elt.client.awardReward.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2011年12月12日
 */
public class AwardRewardAddResponse implements Result {

	String LotId;



	public String getLotId() {
		return LotId;
	}



	public void setLotId(String lotId) {
		LotId = lotId;
	}



	public AwardRewardAddResponse() {

	}




}
