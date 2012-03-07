package com.chinarewards.gwt.elt.client.department.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.department.model.DepartmentCriteria;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentListPresenter.DepartmentListDisplay;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentResponse;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DepartmentListViewAdapter extends BaseDataProvider<DepartmentClient> {

	final DispatchAsync dispatch;
	final DepartmentCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final DepartmentListDisplay display;

	public DepartmentListViewAdapter(DispatchAsync dispatch, DepartmentCriteria criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, DepartmentListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		if (getSorting() != null) {
			criteria.setSorting(getSorting());
		}
		dispatch.execute(new SearchDepartmentRequest(criteria, sessionManager
				.getSession().getCorporationId(), sessionManager.getSession()
				.getUserRoles(), sessionManager.getSession().getToken()),
				new AsyncCallback<SearchDepartmentResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchDepartmentResponse response) {
//						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
//						display.setDataCount(response.getTotal()+"");
					}

				});
	}
	// }

}
