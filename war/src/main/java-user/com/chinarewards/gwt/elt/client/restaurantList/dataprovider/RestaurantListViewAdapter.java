package com.chinarewards.gwt.elt.client.restaurantList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.restaurantList.model.RestaurantListClient;
import com.chinarewards.gwt.elt.client.restaurantList.model.RestaurantListCriteria;
import com.chinarewards.gwt.elt.client.restaurantList.presenter.RestaurantListPresenter.RestaurantListDisplay;
import com.chinarewards.gwt.elt.client.restaurantList.request.SearchRestaurantListRequest;
import com.chinarewards.gwt.elt.client.restaurantList.request.SearchRestaurantListResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RestaurantListViewAdapter extends BaseDataProvider<RestaurantListClient> {

	final DispatchAsync dispatch;
	final RestaurantListDisplay display;
	RestaurantListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public RestaurantListViewAdapter(DispatchAsync dispatch,
			RestaurantListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, RestaurantListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<RestaurantListClient> list = new ArrayList<RestaurantListClient>();
		// for (int i = start; i < start + length; i++) {
		// RestaurantListClient item = new RestaurantListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(RestaurantListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchRestaurantListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchRestaurantListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchRestaurantListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(RestaurantListCriteria criteria) {
		this.criteria = criteria;
	}

	private RestaurantListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new RestaurantListCriteria();
		}

		return criteria;
	}
}
