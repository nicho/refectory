/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdResponse;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchGiftByIdRequest implements
		Action<SearchGiftByIdResponse> {

	private String id;

	public SearchGiftByIdRequest() {
	}

	public SearchGiftByIdRequest(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
