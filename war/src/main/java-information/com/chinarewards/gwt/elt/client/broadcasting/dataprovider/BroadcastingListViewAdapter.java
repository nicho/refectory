package com.chinarewards.gwt.elt.client.broadcasting.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListClient;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria;
import com.chinarewards.gwt.elt.client.broadcasting.presenter.BroadcastingListPresenter.BroadcastingListDisplay;
import com.chinarewards.gwt.elt.client.broadcasting.request.SearchBroadcastingListRequest;
import com.chinarewards.gwt.elt.client.broadcasting.request.SearchBroadcastingListResponse;
import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class BroadcastingListViewAdapter extends BaseDataProvider<BroadcastingListClient> {

	final DispatchAsync dispatch;
	final BroadcastingListDisplay display;
	BroadcastingListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public BroadcastingListViewAdapter(DispatchAsync dispatch,
			BroadcastingListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, BroadcastingListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<BroadcastingListClient> list = new ArrayList<BroadcastingListClient>();
		// for (int i = start; i < start + length; i++) {
		// BroadcastingListClient item = new BroadcastingListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(BroadcastingListStatus.TO_BE_ISSUE);
		// list.add(item);
		// }
		//
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new SearchBroadcastingListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchBroadcastingListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchBroadcastingListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(BroadcastingListCriteria criteria) {
		this.criteria = criteria;
	}

	private BroadcastingListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new BroadcastingListCriteria();
		}

		return criteria;
	}
}
