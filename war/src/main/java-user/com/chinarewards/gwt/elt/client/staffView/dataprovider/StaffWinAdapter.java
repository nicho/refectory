package com.chinarewards.gwt.elt.client.staffView.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.staffView.model.StaffWinClient;
import com.chinarewards.gwt.elt.client.staffView.model.StaffWinCriteria;
import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenter.StaffViewDisplay;
import com.chinarewards.gwt.elt.client.staffView.request.StaffWinRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffWinResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StaffWinAdapter extends BaseDataProvider<StaffWinClient> {

	final DispatchAsync dispatch;
	final StaffViewDisplay display;
	StaffWinCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public StaffWinAdapter(DispatchAsync dispatch,
			StaffWinCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, StaffViewDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<StaffWinClient> list = new ArrayList<StaffWinClient>();
		// for (int i = start; i < start + length; i++) {
		// StaffWinClient item = new StaffWinClient();
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
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new StaffWinRequest(getCriteria()), new AsyncCallback<StaffWinResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(StaffWinResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(StaffWinCriteria criteria) {
		this.criteria = criteria;
	}

	private StaffWinCriteria getCriteria() {
		if (criteria == null) {
			criteria = new StaffWinCriteria();
		}

		return criteria;
	}
}
