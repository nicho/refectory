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
public class InitCorpBudgetRequest implements Action<InitCorpBudgetResponse> {

	
	private UserSession userSession;
	

	public InitCorpBudgetRequest() {
	}

	public InitCorpBudgetRequest(UserSession userSession) {
		
		this.userSession = userSession;
	}

	

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
