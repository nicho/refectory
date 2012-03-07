package com.chinarewards.gwt.elt.server.budget;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.budget.search.DepartmentBudgetVo;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.gwt.elt.client.budget.model.DepBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.SearchDepBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.SearchDepBudgetResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年1月10日 16:09:07
 */
public class SearchDepBudgetHandler extends
		BaseActionHandler<SearchDepBudgetRequest, SearchDepBudgetResponse> {

	@InjectLogger
	Logger logger;

	BudgetService budgetService;

	@Inject
	public SearchDepBudgetHandler(BudgetService budgetService) {
		this.budgetService = budgetService;

	}

	@Override
	public SearchDepBudgetResponse execute(SearchDepBudgetRequest request,
			ExecutionContext context) throws DispatchException {

		SearchDepBudgetResponse resp = new SearchDepBudgetResponse();

		DepBudgetVo clientVo = request.getBudgetVo();
		DepartmentBudgetVo serviceVo = adapter(clientVo);//从客户端转到model
		PageStore<DepartmentBudgetVo> budgetPage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());
		
		budgetPage = budgetService.deptBudgetList(uc, serviceVo);
		resp.setTotal(budgetPage.getResultCount());
		resp.setResult(adapterToClient(budgetPage.getResultList()));//从服务端转为客户端

		return resp;
	}

	private DepartmentBudgetVo adapter(DepBudgetVo criteria) {
		DepartmentBudgetVo vo = new DepartmentBudgetVo();
			vo.setId(criteria.getId());
			vo.setBudgetIntegral(criteria.getBudgetIntegral());
			vo.setCorpBudgetId(criteria.getCorpBudgetId());
			vo.setDepartmentName(criteria.getDepartmentName());
			
			vo.setDepartmentId(criteria.getDepartmentId());
		    vo.setUseIntegeral(criteria.getUseIntegeral());
		    vo.setDeleted(0);//查没有删除的数据
		if (criteria.getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(criteria.getPagination().getLimit());
			detail.setStart(criteria.getPagination().getStart());

			vo.setPaginationDetail(detail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			vo.setSortingDetail(sortingDetail);
		}
		return vo;
	}
	//从服务端得到的数据到客户端在列表显示的数据
		public static List<DepBudgetVo> adapterToClient(List<DepartmentBudgetVo> service) {
			List<DepBudgetVo> resultList = new ArrayList<DepBudgetVo>();

			for (DepartmentBudgetVo item : service) {
				DepBudgetVo client = new DepBudgetVo();
				client.setId(item.getId());
				client.setBudgetIntegral(item.getBudgetIntegral());
				client.setCorpBudgetId(item.getCorpBudgetId());
				client.setDepartmentName(item.getDepartmentName());
				client.setUseIntegeral(item.getUseIntegeral());
				client.setPeople(item.getPeople());
				resultList.add(client);
			}

			return resultList;
		}
	@Override
	public Class<SearchDepBudgetRequest> getActionType() {
		return SearchDepBudgetRequest.class;
	}

	@Override
	public void rollback(SearchDepBudgetRequest req, SearchDepBudgetResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
