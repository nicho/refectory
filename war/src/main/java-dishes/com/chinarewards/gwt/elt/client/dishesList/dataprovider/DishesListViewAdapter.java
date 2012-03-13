package com.chinarewards.gwt.elt.client.dishesList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.dishesList.model.DishesListClient;
import com.chinarewards.gwt.elt.client.dishesList.model.DishesListCriteria;
import com.chinarewards.gwt.elt.client.dishesList.presenter.DishesListPresenter.DishesListDisplay;
import com.chinarewards.gwt.elt.client.dishesList.request.SearchDishesListRequest;
import com.chinarewards.gwt.elt.client.dishesList.request.SearchDishesListResponse;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DishesListViewAdapter extends BaseDataProvider<DishesListClient> {

	final DispatchAsync dispatch;
	final DishesListDisplay display;
	DishesListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public DishesListViewAdapter(DispatchAsync dispatch,
			DishesListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, DishesListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<DishesListClient> list = new ArrayList<DishesListClient>();
		// for (int i = start; i < start + length; i++) {
		// DishesListClient item = new DishesListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(DishesListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchDishesListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchDishesListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchDishesListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(DishesListCriteria criteria) {
		this.criteria = criteria;
	}

	private DishesListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new DishesListCriteria();
		}

		return criteria;
	}
}
