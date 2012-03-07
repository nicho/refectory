/**
 * 
 */
package com.chinarewards.gwt.elt.client.budget.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;

/**
 * @author lw
 * @since 2012年1月20日 19:00:32
 */
public class InitCorpBudgetResponse implements Result {

	private List<CorpBudgetVo> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<CorpBudgetVo> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<CorpBudgetVo> result) {

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
