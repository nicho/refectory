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
public class AddDepartmentBudgetRequest implements Action<AddDepartmentBudgetResponse> {

	
	private UserSession userSession;
	private DepBudgetVo  departmentBudgetVo;
	private  String operate; 
   
	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public DepBudgetVo getDepartmentBudgetVo() {
		return departmentBudgetVo;
	}

	public void setDepartmentBudgetVo(DepBudgetVo departmentBudgetVo) {
		this.departmentBudgetVo = departmentBudgetVo;
	}

	
	public AddDepartmentBudgetRequest() {
	}

	public AddDepartmentBudgetRequest(UserSession userSession,DepBudgetVo  departmentBudgetVo,String operate) {
		
		this.userSession = userSession;
		this.departmentBudgetVo = departmentBudgetVo;
		this.operate = operate;
	}

	

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
