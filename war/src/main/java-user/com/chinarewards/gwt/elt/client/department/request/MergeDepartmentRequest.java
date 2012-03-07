package com.chinarewards.gwt.elt.client.department.request;

import net.customware.gwt.dispatch.shared.Action;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class MergeDepartmentRequest implements Action<MergeDepartmentResponse> {

	private String departmentIds;
	private String departmentName;
	private String leaderId;
	private UserSession userSession;

	public MergeDepartmentRequest() {
	}
	
	public MergeDepartmentRequest(String departmentIds,String departmentName,String leaderId) {
		this.departmentIds = departmentIds;
		this.departmentName=departmentName;
		this.leaderId=leaderId;
	}
	
	public MergeDepartmentRequest(String departmentIds, UserSession userSession) {
		this.departmentIds = departmentIds;
		this.userSession = userSession;
	}




	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}
