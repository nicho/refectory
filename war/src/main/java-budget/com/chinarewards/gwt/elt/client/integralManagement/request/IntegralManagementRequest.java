/**
 * 
 */
package com.chinarewards.gwt.elt.client.integralManagement.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年2月10日 11:02:38
 */
public class IntegralManagementRequest implements
		Action<IntegralManagementResponse> {

	private String corporationId;

	public IntegralManagementRequest() {
	}

	public IntegralManagementRequest(String corporationId) {

		this.corporationId = corporationId;

	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

}
