/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastSave.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月21日 13:56:39
 */
public class BroadcastUpdateRequest implements
		Action<BroadcastUpdateResponse> {
	String broadcastId;

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	public BroadcastUpdateRequest() {
	}
	public BroadcastUpdateRequest(String broadcastId) {
		this.broadcastId=broadcastId;
	}

}
