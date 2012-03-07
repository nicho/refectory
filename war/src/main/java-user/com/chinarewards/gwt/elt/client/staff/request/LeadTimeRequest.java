/**
 * 
 */
package com.chinarewards.gwt.elt.client.staff.request;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class LeadTimeRequest implements Action<LeadTimeResponse> {
	private UserSession session;
	String staffId;
	int leadTime;
	public LeadTimeRequest(String staffId,int leadTime) {
		this.staffId = staffId;
		this.leadTime = leadTime;
	}

	public UserSession getSession() {
		return session;
	}


	public int getLeadTime() {
		return leadTime;
	}


	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}


	public void setSession(UserSession session) {
		this.session = session;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	
	public LeadTimeRequest() {
	}

	


}
