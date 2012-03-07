package com.chinarewards.gwt.elt.server.budget;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.EditCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.EditCorpBudgetResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditCorpBudgetHandler extends
		BaseActionHandler<EditCorpBudgetRequest, EditCorpBudgetResponse> {

	@InjectLogger
	Logger logger;
	BudgetService budgetService;

	@Inject
	public EditCorpBudgetHandler(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@Override
	public Class<EditCorpBudgetRequest> getActionType() {
		return EditCorpBudgetRequest.class;
	}

	@Override
	public EditCorpBudgetResponse execute(EditCorpBudgetRequest request,
			ExecutionContext context) throws DispatchException {
		logger.debug("AddCorpBudgetResponse , corpBudget:" + request.getCorpBudgetVo().toString());
		logger.debug("AddCorpBudgetResponse ,rewardId=" + request.getCorpBudgetVo().getId());
		
		String corporationId = request.getUserSession().getCorporationId();
		 
		CorpBudgetVo corpBudgetVo = request.getCorpBudgetVo();

		CorpBudget corpBudget = assembleCorpBudget(corpBudgetVo,corporationId);

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserId(request.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession()
				.getUserRoles()));
		
		CorpBudget AddItem = budgetService.saveCorpBudget(uc, corpBudget);
			
		return new EditCorpBudgetResponse(AddItem.getId());
	}

	public  CorpBudget assembleCorpBudget(CorpBudgetVo corpBudgetVo,String corporationId) {
		CorpBudget corpBudget = budgetService.findCorpBudgetByCorpId(corporationId);
		
		if (corpBudget!=null&&corpBudget.getId()!=null) {
			
		} else {
			corpBudget=new  CorpBudget();
			corpBudget.setCorporationId(corporationId);
		}
		
		corpBudget.setBudgetTitle(corpBudgetVo.getBudgetTitle());
		corpBudget.setMoneyType(corpBudgetVo.getMoneyType());
		corpBudget.setBudgetAmount(corpBudgetVo.getBudgetAmount());
		corpBudget.setBudgetIntegral(corpBudgetVo.getBudgetIntegral());
		corpBudget.setBeginDate(corpBudgetVo.getBeginDate());
		corpBudget.setEndDate(corpBudgetVo.getEndDate());
		
//		System.out.println("====="+EditCorpBudgetHandler.class.getName()+"===assembleCorpBudget:"+corpBudget.getBudgetTitle());
		return corpBudget;
	}

	@Override
	public void rollback(EditCorpBudgetRequest action, EditCorpBudgetResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
