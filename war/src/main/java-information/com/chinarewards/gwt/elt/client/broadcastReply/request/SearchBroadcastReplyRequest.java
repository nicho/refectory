/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastReply.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchBroadcastReplyRequest implements Action<SearchBroadcastReplyResponse> {

	private ReplyListCriteria criteria;
	private UserSession session;


	public SearchBroadcastReplyRequest() {
	}

	public ReplyListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(ReplyListCriteria criteria) {
		this.criteria = criteria;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param BroadcastingListVo
	 */
	public SearchBroadcastReplyRequest(ReplyListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
