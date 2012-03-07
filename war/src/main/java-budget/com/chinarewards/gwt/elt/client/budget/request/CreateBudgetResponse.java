package com.chinarewards.gwt.elt.client.budget.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.budget.model.DepartmentBudgetVo;

/**
 * 
 * @author harry
 *@since 0.2.0
 */
public class CreateBudgetResponse implements Result {
	private List<DepartmentBudgetVo> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<DepartmentBudgetVo> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<DepartmentBudgetVo> result) {

		this.result = result;
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
