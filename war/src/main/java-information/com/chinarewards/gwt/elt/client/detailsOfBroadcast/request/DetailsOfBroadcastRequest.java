/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfBroadcast.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月21日 13:56:39
 */
public class DetailsOfBroadcastRequest implements
		Action<DetailsOfBroadcastResponse> {
	String broadcastId;

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	public DetailsOfBroadcastRequest() {
	}
	public DetailsOfBroadcastRequest(String broadcastId) {
		this.broadcastId=broadcastId;
	}

}
