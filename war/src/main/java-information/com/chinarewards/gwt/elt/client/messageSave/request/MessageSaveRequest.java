/**
 * 
 */
package com.chinarewards.gwt.elt.client.messageSave.request;

import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class MessageSaveRequest implements Action<MessageSaveResponse> {
	private UserSession session;
	String broadcastId;
	String content;
	List<String[]> organList;
	Date broadcastingTimeStart;
	Date broadcastingTimeEnd;
	
	boolean allowreplies;
	

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public List<String[]> getOrganList() {
		return organList;
	}


	public void setOrganList(List<String[]> organList) {
		this.organList = organList;
	}


	public UserSession getSession() {
		return session;
	}


	public void setSession(UserSession session) {
		this.session = session;
	}




	public String getBroadcastId() {
		return broadcastId;
	}


	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
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


	public MessageSaveRequest() {
	}

	


}
