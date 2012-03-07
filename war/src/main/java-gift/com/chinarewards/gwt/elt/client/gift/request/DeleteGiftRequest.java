/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import com.chinarewards.gwt.elt.client.gift.request.DeleteGiftResponse;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年1月13日 09:52:18
 */
public class DeleteGiftRequest implements Action<DeleteGiftResponse> {

	private String giftId;
	private String userId;



	public DeleteGiftRequest() {
	}

	public DeleteGiftRequest(String giftId,String userId) {
		this.giftId = giftId;
		this.userId=userId;

	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
