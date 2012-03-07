/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderSubmit.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author nicho
 * @since 2012年2月1日 15:07:52
 */
public class OrderSubmitResponse implements Result {

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public OrderSubmitResponse() {

	}

	public OrderSubmitResponse(String result) {
		this.result = result;
	}

}
