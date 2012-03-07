package com.chinarewards.gwt.elt.server.budget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.domain.budget.DepartmentBudget;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetResponse;
import com.chinarewards.gwt.elt.client.support.UserSession;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author lw
 * */
public class InitCorpBudgetHandler extends	BaseActionHandler<InitCorpBudgetRequest, InitCorpBudgetResponse> {

	@InjectLogger
	Logger logger;
	BudgetService corpBudgetService;

	@Inject
	public InitCorpBudgetHandler(BudgetService corpBudgetService) {
		this.corpBudgetService = corpBudgetService;
	}

	@Override
	public Class<InitCorpBudgetRequest> getActionType() {
		return InitCorpBudgetRequest.class;
	}

	@Override
	public InitCorpBudgetResponse execute(InitCorpBudgetRequest action,
			ExecutionContext context) throws DispatchException {
		
         List<UserRoleVo> roleList = Arrays.asList(action.getUserSession().getUserRoles());
         InitCorpBudgetResponse resp = new InitCorpBudgetResponse();
		 if (roleList.contains(UserRoleVo.CORP_ADMIN)) {
		    List<CorpBudget> listVo = corpBudgetService.findCorpBudget(action.getUserSession().getCorporationId());
			
			resp.setResult(adapterToClient(listVo));//从服务端转为客户端
		 
		 } else if (roleList.contains(UserRoleVo.DEPT_MGR)) {
			 List<DepartmentBudget> listVo = corpBudgetService.findDepartBudget(action.getUserSession().getDepartmentId());
			 resp.setResult(adapterToDepClient(listVo));//从服务端转为客户端
		 }
		

		return resp;
	   }
	
        //从服务端得到的数据到客户端在显示的数据企业的当年预算
		public static List<CorpBudgetVo> adapterToClient(List<CorpBudget> service) {
			List<CorpBudgetVo> resultList = new ArrayList<CorpBudgetVo>();

			for (CorpBudget item : service) {
				CorpBudgetVo client = new CorpBudgetVo();
				client.setId(item.getId());
				client.setBudgetIntegral(item.getBudgetIntegral());
				client.setUseIntegeral(item.getUseIntegeral());
				client.setBudgetTitle(item.getBudgetTitle());
				resultList.add(client);
			}

			return resultList;
		}	
		//从服务端得到的数据到客户端在显示的数据部门当年的预算
				public  List<CorpBudgetVo> adapterToDepClient(List<DepartmentBudget> service) {
					List<CorpBudgetVo> resultList = new ArrayList<CorpBudgetVo>();
                  
					for (DepartmentBudget item : service) {
						CorpBudgetVo client = new CorpBudgetVo();
						client.setId(item.getId());
						client.setBudgetIntegral(item.getBudgetIntegral());
						client.setUseIntegeral(item.getUseIntegeral());
						CorpBudget corpVo = corpBudgetService.findCorpBudgetById(item.getCorpBudgetId());
						client.setBudgetTitle(corpVo.getBudgetTitle());
						resultList.add(client);
					
                   }
					return resultList;
				}	


	@Override
	public void rollback(InitCorpBudgetRequest arg0,
			InitCorpBudgetResponse arg1, ExecutionContext arg2)
			throws DispatchException {
		// TODO Auto-generated method stub
		
	}

}
