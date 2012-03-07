/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author lw
 * @since 2012年1月13日 09:52:24
 */
public class DeleteOrderResponse implements Result {

	private String total;

	public DeleteOrderResponse() {
	}

	public DeleteOrderResponse(String total) {
		this.total = total;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
}
