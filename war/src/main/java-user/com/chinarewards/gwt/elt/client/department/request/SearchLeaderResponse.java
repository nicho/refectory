package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.department.model.SearchLeaderResult;

public class SearchLeaderResponse implements Result {

	private SearchLeaderResult result;

	public SearchLeaderResponse() {
	}

	public SearchLeaderResponse(SearchLeaderResult result) {
		this.result = result;
	}

	public SearchLeaderResult getResult() {
		return result;
	}

	public void setResult(SearchLeaderResult result) {
		this.result = result;
	}

}
