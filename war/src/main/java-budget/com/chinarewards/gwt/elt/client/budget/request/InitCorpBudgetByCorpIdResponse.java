/**
 * 
 */
package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;

/**
 * @author yanrui
 * @since
 */
public class InitCorpBudgetByCorpIdResponse implements Result {

	private CorpBudgetVo corpBudgetVo;

	public InitCorpBudgetByCorpIdResponse() {

	}

	public InitCorpBudgetByCorpIdResponse(CorpBudgetVo corpBudgetVo) {
		this.corpBudgetVo = corpBudgetVo;
	}

	public CorpBudgetVo getCorpBudgetVo() {
		return corpBudgetVo;
	}

	public void setCorpBudgetVo(CorpBudgetVo corpBudgetVo) {
		this.corpBudgetVo = corpBudgetVo;
	}

}
