/**
 * 
 */
package com.chinarewards.gwt.elt.client.message.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.message.model.MessageListCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchMessageListRequest implements Action<SearchMessageListResponse> {

	private MessageListCriteria criteria;
	private UserSession session;


	public SearchMessageListRequest() {
	}

	public MessageListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(MessageListCriteria criteria) {
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
	 * @param MessageListVo
	 */
	public SearchMessageListRequest(MessageListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
