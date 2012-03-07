package com.chinarewards.gwt.elt.client.budget.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.budget.model.DepartmentBudgetVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * 
 * @author harry
 *@since 0.2.0
 */
public class CreateBudgetRequest implements Action<CreateBudgetResponse> {

	
	
	public DepartmentBudgetVo budgetVo;
	private UserSession userSession;
	public CreateBudgetRequest() {

	}

	public CreateBudgetRequest(DepartmentBudgetVo budgetVo,UserSession userSession) {
		this.budgetVo = budgetVo;
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public DepartmentBudgetVo getBudgetVo() {
		return budgetVo;
	}

	public void setBudgetVo(DepartmentBudgetVo budgetVo) {
		this.budgetVo = budgetVo;
	}

	
	
}
