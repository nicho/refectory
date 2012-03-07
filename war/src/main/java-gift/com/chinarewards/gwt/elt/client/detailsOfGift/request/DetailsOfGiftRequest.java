/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfGift.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年2月1日 16:55:09
 */
public class DetailsOfGiftRequest implements Action<DetailsOfGiftResponse> {


	private String giftId;




	public DetailsOfGiftRequest() {
	}

	public DetailsOfGiftRequest(String giftId) {

		this.giftId=giftId;

	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	
	



}
