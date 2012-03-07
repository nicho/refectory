/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderConfirmation.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年1月31日 18:52:22
 */
public class OrderConfirmationRequest implements Action<OrderConfirmationResponse> {


	private String giftId;
	private String staffId;



	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public OrderConfirmationRequest() {
	}

	public OrderConfirmationRequest(String giftId,String staffId) {
		this.giftId = giftId;
		this.staffId=staffId;

	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	



}
