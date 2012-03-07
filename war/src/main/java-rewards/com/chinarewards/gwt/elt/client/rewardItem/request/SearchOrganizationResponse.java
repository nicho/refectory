/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.rewards.model.StaffOrDepartmentAC;

/**
 * Models the response after search orgnization request.
 * 
 * @author yanxin
 * @since 0.1.0 2010-12-16
 */
public class SearchOrganizationResponse implements Result {

	/**
	 * 部门和员工
	 */
	private List<StaffOrDepartmentAC> list;

	public SearchOrganizationResponse(List<StaffOrDepartmentAC> list) {
		this.list = list;
	}

	public SearchOrganizationResponse() {

	}

	public List<StaffOrDepartmentAC> getList() {
		return list;
	}

}
