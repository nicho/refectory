package com.chinarewards.gwt.elt.client.budget.provider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.budget.model.DepBudgetVo;
import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenter.CreateBudgetDisplay;
import com.chinarewards.gwt.elt.client.budget.request.SearchDepBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.SearchDepBudgetResponse;
import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DepBudgetListAdapter extends BaseDataProvider<DepBudgetVo> {

	final DispatchAsync dispatch;
	final DepBudgetVo criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final CreateBudgetDisplay display;

	public DepBudgetListAdapter(DispatchAsync dispatch, DepBudgetVo criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, CreateBudgetDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
//		 if (!GWT.isScript()) {
//		 List<DepBudgetVo> list = new ArrayList<DepBudgetVo>();
//		 for (int i = start; i < start + length; i++) {
//			 DepBudgetVo item = new DepBudgetVo();
//		 item.setId("id" + i);
//		 item.setDepartmentName("gift" + i);
//		 item.setBudgetIntegral(i);
//		 item.setUseIntegeral(100);
//		 list.add(item);
//		 }
//		
//		 updateRowData(start, list);
//		 updateRowCount(100, true);
//		 } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		if (getSorting() != null) {
			criteria.setSorting(getSorting());
		}
		dispatch.execute(new SearchDepBudgetRequest(criteria, sessionManager.getSession()),
				new AsyncCallback<SearchDepBudgetResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert("查询失败");
					}

					@Override
					public void onSuccess(SearchDepBudgetResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						
					}

				});
//	}
	 }

}
