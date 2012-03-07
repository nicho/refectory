/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author lw
 * @since 2012年1月13日 09:52:24
 */
public class OrderBoxResponse implements Result {

	private int total;

	public OrderBoxResponse() {
	}

	public OrderBoxResponse(int total) {
		this.total = total;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
