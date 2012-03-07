package com.chinarewards.gwt.elt.client.chooseOrganization.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * 
 * 
 * @author cyril
 *
 */
public class ChooseOrganizationRequest implements Action<ChooseOrganizationResponse> {

	private OrganSearchCriteria criteria;

	private UserSession session;


	private boolean limitDataByUserRole = false;

	public ChooseOrganizationRequest() {
	}

	public ChooseOrganizationRequest(OrganSearchCriteria criteria,
			UserSession session, boolean limitDataByUserRole) {
		this.criteria = criteria;
		this.session = session;
		this.limitDataByUserRole = limitDataByUserRole;
	}

	public OrganSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(OrganSearchCriteria criteria) {
		this.criteria = criteria;
	}



	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * Whether to limit data visible by the caller user (LoginContext). Default
	 * value is <code>false</code>.
	 * 
	 * @return the limitDataByUserRole
	 */
	public boolean isLimitDataByUserRole() {
		return limitDataByUserRole;
	}

	/**
	 * @param limitDataByUserRole
	 *            the limitDataByUserRole to set
	 */
	public void setLimitDataByUserRole(boolean limitDataByUserRole) {
		this.limitDataByUserRole = limitDataByUserRole;
	}

}
