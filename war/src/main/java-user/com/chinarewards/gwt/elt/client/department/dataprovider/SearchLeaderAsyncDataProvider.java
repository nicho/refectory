package com.chinarewards.gwt.elt.client.department.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.department.model.LeaderSearchCriteria;
import com.chinarewards.gwt.elt.client.department.request.SearchLeaderRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchLeaderResponse;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SearchLeaderAsyncDataProvider extends
		BaseDataProvider<StaffClient> {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final LeaderSearchCriteria criteria;
	final boolean filterByAcl;

	public SearchLeaderAsyncDataProvider(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			LeaderSearchCriteria criteria, boolean filterByAcl) {
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.criteria = criteria;
		this.filterByAcl = filterByAcl;
	}

	@Override
	public void fetchData(final int start, final int length) {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		criteria.setSorting(getSorting());
		dispatch.execute(
				new SearchLeaderRequest(criteria, sessionManager
						.getSession(), filterByAcl),
				new AsyncCallback<SearchLeaderResponse>() {

					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchLeaderResponse response) {
						updateRowData(start, response.getResult().getResult());
						updateRowCount(response.getResult().getTotal(), true);
					}
				});
	}

}
