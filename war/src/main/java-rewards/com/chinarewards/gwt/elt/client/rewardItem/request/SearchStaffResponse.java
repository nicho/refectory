package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.StaffSearchResult;

public class SearchStaffResponse implements Result {

	private StaffSearchResult result;

	public SearchStaffResponse() {
	}

	public SearchStaffResponse(StaffSearchResult result) {
		this.result = result;
	}

	public StaffSearchResult getResult() {
		return result;
	}

	public void setResult(StaffSearchResult result) {
		this.result = result;
	}

}
