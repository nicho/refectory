package com.chinarewards.gwt.elt.client.orderList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.orderList.model.OrderListClient;
import com.chinarewards.gwt.elt.client.orderList.model.OrderListCriteria;
import com.chinarewards.gwt.elt.client.orderList.presenter.OrderListPresenter.OrderListDisplay;
import com.chinarewards.gwt.elt.client.orderList.request.SearchOrderListRequest;
import com.chinarewards.gwt.elt.client.orderList.request.SearchOrderListResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class OrderListViewAdapter extends BaseDataProvider<OrderListClient> {

	final DispatchAsync dispatch;
	final OrderListDisplay display;
	OrderListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public OrderListViewAdapter(DispatchAsync dispatch,
			OrderListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, OrderListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<OrderListClient> list = new ArrayList<OrderListClient>();
		// for (int i = start; i < start + length; i++) {
		// OrderListClient item = new OrderListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(OrderListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchOrderListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchOrderListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchOrderListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(OrderListCriteria criteria) {
		this.criteria = criteria;
	}

	private OrderListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new OrderListCriteria();
		}

		return criteria;
	}
}
