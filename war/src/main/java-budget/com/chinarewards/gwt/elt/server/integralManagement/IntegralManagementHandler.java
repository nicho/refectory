package com.chinarewards.gwt.elt.server.integralManagement;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.model.budget.search.IntegralManagementVo;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.integralManagement.model.Category;
import com.chinarewards.gwt.elt.client.integralManagement.request.IntegralManagementRequest;
import com.chinarewards.gwt.elt.client.integralManagement.request.IntegralManagementResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author nicho
 * */
public class IntegralManagementHandler extends
		BaseActionHandler<IntegralManagementRequest, IntegralManagementResponse> {

	@InjectLogger
	Logger logger;

	BudgetService budgetService;

	@Inject
	public IntegralManagementHandler(BudgetService budgetService) {
		this.budgetService = budgetService;
	}
	@Override
	public IntegralManagementResponse execute(IntegralManagementRequest action,
			ExecutionContext context) throws DispatchException {
		List<Category> rs=new ArrayList<Category>();
		//查询当前财年..待添加
		CorpBudget corpBudget = budgetService.findCorpBudgetByCorpId(action.getCorporationId());
	if(corpBudget !=null){
		 List<IntegralManagementVo> integralManagementVoList=budgetService.getIntegralManagementList(action.getCorporationId(),corpBudget.getId());
		
		for (IntegralManagementVo vo:integralManagementVoList) {
			Category c=new Category(vo.getDepartmentName(), (int)vo.getBudgetIntegral()+"", (int)vo.getUseIntegeral()+"",vo.getDepartmentId(),vo.isLeaf(),vo.getParentId());
			rs.add(c);
		}
		
		return new IntegralManagementResponse(rs,corpBudget.getBudgetIntegral(),corpBudget.getBudgetIntegral()-corpBudget.getUseIntegeral());
		}
			
	    return null;
	}
	
	
	
	@Override
	public Class<IntegralManagementRequest> getActionType() {
		return IntegralManagementRequest.class;
	}

	@Override
	public void rollback(IntegralManagementRequest arg0,
			IntegralManagementResponse arg1, ExecutionContext arg2)
			throws DispatchException {
		
	}

	
}
