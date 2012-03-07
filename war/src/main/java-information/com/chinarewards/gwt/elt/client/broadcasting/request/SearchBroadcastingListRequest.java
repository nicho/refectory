/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcasting.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchBroadcastingListRequest implements Action<SearchBroadcastingListResponse> {

	private BroadcastingListCriteria criteria;
	private UserSession session;


	public SearchBroadcastingListRequest() {
	}

	public BroadcastingListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(BroadcastingListCriteria criteria) {
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
	public SearchBroadcastingListRequest(BroadcastingListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
