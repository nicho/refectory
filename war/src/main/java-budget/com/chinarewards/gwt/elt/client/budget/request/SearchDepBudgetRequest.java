/**
 * 
 */
package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.budget.model.DepBudgetVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author lw
 * @since 2012年1月20日 19:00:40
 */
public class SearchDepBudgetRequest implements Action<SearchDepBudgetResponse> {

	public DepBudgetVo budgetVo;
	private UserSession userSession;
	

	public SearchDepBudgetRequest() {
	}

	public SearchDepBudgetRequest(DepBudgetVo budgetVo,UserSession userSession) {
		this.budgetVo = budgetVo;
		this.userSession = userSession;
	}

	
	public DepBudgetVo getBudgetVo() {
		return budgetVo;
	}

	public void setBudgetVo(DepBudgetVo budgetVo) {
		this.budgetVo = budgetVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
