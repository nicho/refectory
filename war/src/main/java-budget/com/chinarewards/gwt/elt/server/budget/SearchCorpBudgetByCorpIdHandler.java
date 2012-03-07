package com.chinarewards.gwt.elt.server.budget;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.SearchCorpBudgetByCorpIdRequest;
import com.chinarewards.gwt.elt.client.budget.request.SearchCorpBudgetByCorpIdResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchCorpBudgetByCorpIdHandler
		extends
		BaseActionHandler<SearchCorpBudgetByCorpIdRequest, SearchCorpBudgetByCorpIdResponse> {
	@InjectLogger
	Logger logger;

	BudgetService budgetService;

	@Inject
	public SearchCorpBudgetByCorpIdHandler(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@Override
	public SearchCorpBudgetByCorpIdResponse execute(
			SearchCorpBudgetByCorpIdRequest request, ExecutionContext context)
			throws DispatchException {
		logger.debug(" parameters:{}", request.getCoprid());
		CorpBudget corpBudget = budgetService.findCorpBudgetByCorpId(request
				.getCoprid());
		return new SearchCorpBudgetByCorpIdResponse(adapter(corpBudget));

	}

	private CorpBudgetVo adapter(CorpBudget corpBudget) {
		CorpBudgetVo corpBudgetVo = new CorpBudgetVo();
		if(corpBudget!=null){
			corpBudgetVo.setId(corpBudget.getId());
			corpBudgetVo.setCorporationId(corpBudget.getCorporationId());
			corpBudgetVo.setBeginDate(corpBudget.getBeginDate());
			corpBudgetVo.setEndDate(corpBudget.getEndDate());
			corpBudgetVo.setBudgetAmount(corpBudget.getBudgetAmount());
			corpBudgetVo.setBudgetIntegral(corpBudget.getBudgetIntegral());
			corpBudgetVo.setMoneyType(corpBudget.getMoneyType());
		}else{
			corpBudgetVo.setId("");
		}		

		return corpBudgetVo;
	}

	@Override
	public Class<SearchCorpBudgetByCorpIdRequest> getActionType() {
		return SearchCorpBudgetByCorpIdRequest.class;
	}

	@Override
	public void rollback(SearchCorpBudgetByCorpIdRequest arg0,
			SearchCorpBudgetByCorpIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
