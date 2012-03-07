/**
 * 
 */
package com.chinarewards.gwt.elt.client.hrbox.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author lw
 * @since 2012年1月13日 09:52:24
 */
public class HrBoxResponse implements Result {

	private int total;
	private String week;

	public HrBoxResponse() {
	}

	public HrBoxResponse(int total,String week) {
		this.total = total;
		this.week = week;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
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
