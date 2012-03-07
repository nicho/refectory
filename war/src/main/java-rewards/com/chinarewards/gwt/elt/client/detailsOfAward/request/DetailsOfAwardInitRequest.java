/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfAward.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2011年12月22日 10:14:52
 */
public class DetailsOfAwardInitRequest implements Action<DetailsOfAwardInitResponse> {

	private String awardsId;//奖项ID

	public String getAwardsId() {
		return awardsId;
	}

	public void setAwardsId(String awardsId) {
		this.awardsId = awardsId;
	}

	
	/**
	 * For serialization
	 */
	public DetailsOfAwardInitRequest() {
	}

	public DetailsOfAwardInitRequest(String awardsId) {
		this.awardsId=awardsId;
	}
}
