/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffHeavenIndex.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class StaffHeavenIndexRequest implements Action<StaffHeavenIndexResponse> {

	private StaffHeavenIndexCriteria criteria;
	private UserSession session;


	public StaffHeavenIndexRequest() {
	}

	public StaffHeavenIndexCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(StaffHeavenIndexCriteria criteria) {
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
	 * @param StaffHeavenIndexVo
	 */
	public StaffHeavenIndexRequest(StaffHeavenIndexCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
