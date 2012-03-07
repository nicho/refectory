/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria.GiftStatus;
import com.chinarewards.gwt.elt.client.gift.request.UpdateGiftStatusResponse;

/**
 * @author nicho
 * @since 2012年1月13日 09:52:18
 */
public class UpdateGiftStatusRequest implements Action<UpdateGiftStatusResponse> {

	private String giftId;
	private String userId;
	private GiftStatus status;



	public GiftStatus getStatus() {
		return status;
	}

	public void setStatus(GiftStatus status) {
		this.status = status;
	}

	public UpdateGiftStatusRequest() {
	}

	public UpdateGiftStatusRequest(String giftId,String userId,GiftStatus status) {
		this.giftId = giftId;
		this.userId=userId;
		this.status=status;

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
