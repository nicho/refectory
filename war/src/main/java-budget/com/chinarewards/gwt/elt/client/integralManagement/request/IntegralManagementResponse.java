/**
 * 
 */
package com.chinarewards.gwt.elt.client.integralManagement.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.integralManagement.model.Category;

/**
 * @author nicho
 * @since 2012年1月9日 19:00:32
 */
public class IntegralManagementResponse implements Result {

	private List<Category> result;
	private double budgetIntegral;//预算积分
    private double useIntegeral;  //已用积分
	public double getBudgetIntegral() {
		return budgetIntegral;
	}

	public void setBudgetIntegral(double budgetIntegral) {
		this.budgetIntegral = budgetIntegral;
	}

	public double getUseIntegeral() {
		return useIntegeral;
	}

	public void setUseIntegeral(double useIntegeral) {
		this.useIntegeral = useIntegeral;
	}

	public IntegralManagementResponse(List<Category> result,double budgetIntegral,double useIntegeral) {
		this.result = result;
		this.budgetIntegral=budgetIntegral;
		this.useIntegeral=useIntegeral;
	}

	public IntegralManagementResponse() {
	}

	public List<Category> getResult() {
		return result;
	}

	public void setResult(List<Category> result) {
		this.result = result;
	}

}
