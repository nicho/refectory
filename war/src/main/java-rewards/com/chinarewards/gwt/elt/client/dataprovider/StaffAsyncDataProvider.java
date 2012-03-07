package com.chinarewards.gwt.elt.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchStaffRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchStaffResponse;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffSearchCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StaffAsyncDataProvider extends BaseDataProvider<StaffClient> {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final StaffSearchCriteria criteria;
	final boolean filterByAcl;

	public StaffAsyncDataProvider(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			StaffSearchCriteria criteria, boolean filterByAcl) {
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.criteria = criteria;
		this.filterByAcl = filterByAcl;
	}

	@Override
	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<StaffClient> list = new ArrayList<StaffClient>();
		// for (int i = start; i < start + length; i++) {
		// list.add(new StaffClient("" + i,
		// criteria.getKey() == null ? "name" : criteria.getKey()
		// + i, "cardNo" + i, "deptName" + i, "email" + i));
		// }
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		criteria.setSorting(getSorting());
		dispatch.execute(
				new SearchStaffRequest(criteria, sessionManager.getSession(),filterByAcl), new AsyncCallback<SearchStaffResponse>() {

					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchStaffResponse response) {
						updateRowData(start, response.getResult().getResult());
						updateRowCount(response.getResult().getTotal(), true);
					}
				});
	}
	// }

}
