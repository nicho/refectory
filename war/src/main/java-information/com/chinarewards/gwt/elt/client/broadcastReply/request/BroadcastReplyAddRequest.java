/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastReply.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class BroadcastReplyAddRequest implements Action<BroadcastReplyAddResponse> {

	private String broadcastId;
	private String replyContent;
	private UserSession session;
	private String replyParentId;

	public BroadcastReplyAddRequest() {
	}

	

	public String getReplyParentId() {
		return replyParentId;
	}



	public void setReplyParentId(String replyParentId) {
		this.replyParentId = replyParentId;
	}



	public String getReplyContent() {
		return replyContent;
	}



	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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



	/**
	 * 
	 * @param BroadcastingListVo
	 */
	public BroadcastReplyAddRequest(String broadcastId,String replyContent,UserSession session) {
		this.broadcastId = broadcastId;
		this.session=session;
		this.replyContent=replyContent;
	}
	public BroadcastReplyAddRequest(String broadcastId,String replyContent,UserSession session,String replyParentId) {
		this.broadcastId = broadcastId;
		this.session=session;
		this.replyContent=replyContent;
		this.replyParentId=replyParentId;
	}


}
