package com.chinarewards.gwt.elt.client.broadcastSave.request;

import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class BroadcastUpdateResponse implements Result {

	String content;
	List<OrganicationClient> receivingObject;
	Date broadcastingTimeStart;
	Date broadcastingTimeEnd;
	boolean allowreplies;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<OrganicationClient> getReceivingObject() {
		return receivingObject;
	}

	public void setReceivingObject(List<OrganicationClient> receivingObject) {
		this.receivingObject = receivingObject;
	}

	public Date getBroadcastingTimeStart() {
		return broadcastingTimeStart;
	}

	public void setBroadcastingTimeStart(Date broadcastingTimeStart) {
		this.broadcastingTimeStart = broadcastingTimeStart;
	}

	public Date getBroadcastingTimeEnd() {
		return broadcastingTimeEnd;
	}

	public void setBroadcastingTimeEnd(Date broadcastingTimeEnd) {
		this.broadcastingTimeEnd = broadcastingTimeEnd;
	}

	public boolean isAllowreplies() {
		return allowreplies;
	}

	public void setAllowreplies(boolean allowreplies) {
		this.allowreplies = allowreplies;
	}

	public BroadcastUpdateResponse() {

	}

}
