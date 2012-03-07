package com.chinarewards.gwt.elt.client.orderHistory.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenter.OrderHistoryDisplay;
import com.chinarewards.gwt.elt.client.orderHistory.request.SearchOrderHistoryRequest;
import com.chinarewards.gwt.elt.client.orderHistory.request.SearchOrderHistoryResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class OrderHistoryDataAdapter extends BaseDataProvider<OrderSearchVo> {

	final DispatchAsync dispatch;
	final OrderSearchVo criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final OrderHistoryDisplay display;

	public OrderHistoryDataAdapter(DispatchAsync dispatch, OrderSearchVo criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, OrderHistoryDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<GiftClient> list = new ArrayList<GiftClient>();
		// for (int i = start; i < start + length; i++) {
		// GiftClient item = new GiftClient();
		// item.setId("id" + i);
		// item.setName("gift" + i);
		// item.setSource("来源"+i);
		// item.setStatus(GiftStatus.SHELF);
		// list.add(item);
		// }
		//
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		if (getSorting() != null) {
			criteria.setSorting(getSorting());
		}
		dispatch.execute(new SearchOrderHistoryRequest(criteria, sessionManager.getSession()),
				new AsyncCallback<SearchOrderHistoryResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchOrderHistoryResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal()+"");
					}

				});
	}
	// }

}
