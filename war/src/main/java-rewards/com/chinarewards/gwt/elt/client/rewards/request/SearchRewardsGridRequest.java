package com.chinarewards.gwt.elt.client.rewards.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author yanrui
 * @since
 */
public class SearchRewardsGridRequest implements
		Action<SearchRewardsGridResponse> {

	RewardsGridCriteria criteria;
	private UserSession session;

	public SearchRewardsGridRequest() {
	}

	public SearchRewardsGridRequest(RewardsGridCriteria criteria,
			UserSession session) {
		this.criteria = criteria;
		this.session = session;
	}

	public RewardsGridCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(RewardsGridCriteria criteria) {
		this.criteria = criteria;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "SearchRewardsGridRequest [thisAction=" + criteria + "]";
	}

}
