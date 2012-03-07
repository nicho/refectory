/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderConfirmation.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.gift.model.GiftClient;

/**
 * @author nicho
 * @since 2012年1月31日 18:56:03
 */
public class OrderConfirmationResponse implements Result {

	private GiftClient result;
	private double staffBalance;

	public double getStaffBalance() {
		return staffBalance;
	}

	public void setStaffBalance(double staffBalance) {
		this.staffBalance = staffBalance;
	}

	public GiftClient getResult() {
		return result;
	}

	public void setResult(GiftClient result) {
		this.result = result;
	}



}
