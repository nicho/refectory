/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffView.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.staffView.model.StaffWinCriteria;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月15日 11:09:28
 */
public class StaffWinRequest implements Action<StaffWinResponse> {

	private StaffWinCriteria criteria;

	public StaffWinCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(StaffWinCriteria criteria) {
		this.criteria = criteria;
	}

	public StaffWinRequest() {
	}

	public StaffWinRequest(StaffWinCriteria criteria) {
		this.criteria = criteria;
	}

}
