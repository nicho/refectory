/**
 * 
 */
package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author lw
 * @since 2012年1月20日 19:00:32
 */
public class AddDepartmentBudgetResponse implements Result {

	private String message;
    private double oldJf;//修改时返回的旧的积分
	public String getMessage() {
		return message;
	}

	public double getOldJf() {
		return oldJf;
	}

	public void setOldJf(double oldJf) {
		this.oldJf = oldJf;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
	public AddDepartmentBudgetResponse(String message) {
		this.message = message;
	}

	public AddDepartmentBudgetResponse() {

	}
	
}
