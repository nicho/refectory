package com.chinarewards.gwt.elt.server.budget;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetByCorpIdRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetByCorpIdResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class InitCorpBudgetByCorpIdHandler
		extends
		BaseActionHandler<InitCorpBudgetByCorpIdRequest, InitCorpBudgetByCorpIdResponse> {
	@InjectLogger
	Logger logger;

	BudgetService budgetService;

	@Inject
	public InitCorpBudgetByCorpIdHandler(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@Override
	public InitCorpBudgetByCorpIdResponse execute(
			InitCorpBudgetByCorpIdRequest request, ExecutionContext context)
			throws DispatchException {
		logger.debug(" parameters:{}", request.getUserSession().getCorporationId());
		CorpBudget corpBudget = budgetService.findCorpBudgetByCorpId(request.getUserSession().getCorporationId());
		return new InitCorpBudgetByCorpIdResponse(adapter(corpBudget));

	}

	private CorpBudgetVo adapter(CorpBudget corpBudget) {
		CorpBudgetVo corpBudgetVo = new CorpBudgetVo();
		if(corpBudget!=null){			
			corpBudgetVo.setId(corpBudget.getId());
			corpBudgetVo.setBudgetTitle(corpBudget.getBudgetTitle());
			corpBudgetVo.setCorporationId(corpBudget.getCorporationId());
			
			corpBudgetVo.setBeginDate(corpBudget.getBeginDate());
			corpBudgetVo.setEndDate(corpBudget.getEndDate());
			corpBudgetVo.setBudgetAmount(corpBudget.getBudgetAmount());
			corpBudgetVo.setBudgetIntegral(corpBudget.getBudgetIntegral());
			corpBudgetVo.setMoneyType(corpBudget.getMoneyType());
			

//			System.out.println("=======initCorporBudget title:"+corpBudget.getBudgetTitle());
		}else{
			corpBudgetVo.setId("");
		}		

		return corpBudgetVo;
	}

	@Override
	public Class<InitCorpBudgetByCorpIdRequest> getActionType() {
		return InitCorpBudgetByCorpIdRequest.class;
	}

	@Override
	public void rollback(InitCorpBudgetByCorpIdRequest arg0,
			InitCorpBudgetByCorpIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
