/**
 * 
 */
package com.chinarewards.gwt.elt.client.department.request;

import java.util.List;

import com.chinarewards.gwt.elt.client.department.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.department.request.EditDepartmentResponse;
import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class EditDepartmentRequest implements Action<EditDepartmentResponse> {


	private DepartmentVo departmentVo;
	private UserSession userSession;

	List<String> staffIds;

	public EditDepartmentRequest(DepartmentVo departmentVo, UserSession userSession) {
		this.departmentVo = departmentVo;
		this.userSession = userSession;
	}

	/**
	 * For serialization
	 */
	public EditDepartmentRequest() {
	}


	public DepartmentVo getDepartmentVo() {
		return departmentVo;
	}

	public void setDepartmentVo(DepartmentVo departmentVo) {
		this.departmentVo = departmentVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<String> getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(List<String> staffIds) {
		this.staffIds = staffIds;
	}

}
