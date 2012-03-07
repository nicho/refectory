package com.chinarewards.gwt.elt.client.staffList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPrintPresenter.StaffListPrintDisplay;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListRequest;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StaffListPrintAdapter extends BaseDataProvider<StaffListClient> {

	final DispatchAsync dispatch;
	final StaffListPrintDisplay display;
	StaffListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public StaffListPrintAdapter(DispatchAsync dispatch,
			StaffListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, StaffListPrintDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<StaffListClient> list = new ArrayList<StaffListClient>();
		// for (int i = start; i < start + length; i++) {
		// StaffListClient item = new StaffListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(StaffListStatus.TO_BE_ISSUE);
		// list.add(item);
		// }
		//
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(0);
		pagination.setLimit(0);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new SearchStaffListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchStaffListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchStaffListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
			
			}

		});
		// }
	}


	public void setCriteria(StaffListCriteria criteria) {
		this.criteria = criteria;
	}

	private StaffListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new StaffListCriteria();
		}

		return criteria;
	}
}
