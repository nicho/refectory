package com.chinarewards.gwt.elt.client.staffIntegral.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class StaffIntegralResponse implements Result {

	private String staffId;
	private String historyIntegral;
	private String consumptionIntegral;
	private String balanceIntegral;

	public StaffIntegralResponse() {

	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getHistoryIntegral() {
		return historyIntegral;
	}

	public void setHistoryIntegral(String historyIntegral) {
		this.historyIntegral = historyIntegral;
	}

	public String getConsumptionIntegral() {
		return consumptionIntegral;
	}

	public void setConsumptionIntegral(String consumptionIntegral) {
		this.consumptionIntegral = consumptionIntegral;
	}

	public String getBalanceIntegral() {
		return balanceIntegral;
	}

	public void setBalanceIntegral(String balanceIntegral) {
		this.balanceIntegral = balanceIntegral;
	}

}
