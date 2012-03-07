/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * 搜索部门和员工 --- 用于auto-complete快速选择颁奖对象
 * 
 * @author yanxin
 * @since 0.1.0 2010-12-16
 */
public class SearchOrganizationRequest implements
		Action<SearchOrganizationResponse> {

	private String key;
    private String corporationId;
	public SearchOrganizationRequest(String corporationId, String key) {
		this.key = key;
		this.corporationId =corporationId;
	}

	public SearchOrganizationRequest() {

	}

	public String getKey() {
		return key;
	}
	public String getCorporationId(){
		return corporationId;
	}
}
