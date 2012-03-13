/**
 * 
 */
package com.chinarewards.gwt.elt.client.userList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.support.UserSession;
import com.chinarewards.gwt.elt.client.userList.model.UserListCriteria;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchUserListRequest implements Action<SearchUserListResponse> {

	private UserListCriteria criteria;
	private UserSession session;


	public SearchUserListRequest() {
	}

	public UserListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(UserListCriteria criteria) {
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
	 * @param UserListVo
	 */
	public SearchUserListRequest(UserListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
