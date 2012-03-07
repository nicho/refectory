package com.chinarewards.gwt.elt.client.detailsOfBroadcast.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class DetailsOfBroadcastResponse implements Result {

	String content;
	String receivingObject;
	String broadcastingTime;
	String allowreplies;
	String createUser;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceivingObject() {
		return receivingObject;
	}

	public void setReceivingObject(String receivingObject) {
		this.receivingObject = receivingObject;
	}

	public String getBroadcastingTime() {
		return broadcastingTime;
	}

	public void setBroadcastingTime(String broadcastingTime) {
		this.broadcastingTime = broadcastingTime;
	}

	public String getAllowreplies() {
		return allowreplies;
	}

	public void setAllowreplies(String allowreplies) {
		this.allowreplies = allowreplies;
	}

	public DetailsOfBroadcastResponse() {

	}

	public DetailsOfBroadcastResponse(String content, String receivingObject,
			String broadcastingTime, String allowreplies) {
		this.content = content;
		this.receivingObject = receivingObject;
		this.broadcastingTime = broadcastingTime;
		this.allowreplies = allowreplies;

	}

}
