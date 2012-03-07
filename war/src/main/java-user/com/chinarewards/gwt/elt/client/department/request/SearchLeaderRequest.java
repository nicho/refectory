package com.chinarewards.gwt.elt.client.department.request;

import com.chinarewards.gwt.elt.client.department.model.LeaderSearchCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 * test git commit
 */
public class SearchLeaderRequest implements Action<SearchLeaderResponse> {

	private LeaderSearchCriteria criteria;
	private UserSession userSession;
	private boolean isLimitDataByUserRole;

	public SearchLeaderRequest() {

	}

	/**
	 * @param criteria
	 * @param session
	 * @param filterByAcl
	 */
	public SearchLeaderRequest(LeaderSearchCriteria criteria,
			UserSession userSession, boolean isLimitDataByUserRole) {
		this.criteria = criteria;
		this.userSession = userSession;
		this.isLimitDataByUserRole = isLimitDataByUserRole;
	}

	public LeaderSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(LeaderSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public boolean isLimitDataByUserRole() {
		return isLimitDataByUserRole;
	}

	public void setLimitDataByUserRole(boolean isLimitDataByUserRole) {
		this.isLimitDataByUserRole = isLimitDataByUserRole;
	}

}
