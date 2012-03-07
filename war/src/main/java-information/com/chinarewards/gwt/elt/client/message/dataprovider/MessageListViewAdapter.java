package com.chinarewards.gwt.elt.client.message.dataprovider;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.message.model.MessageListClient;
import com.chinarewards.gwt.elt.client.message.model.MessageListCriteria;
import com.chinarewards.gwt.elt.client.message.presenter.MessageListPresenter.MessageListDisplay;
import com.chinarewards.gwt.elt.client.message.request.SearchMessageListRequest;
import com.chinarewards.gwt.elt.client.message.request.SearchMessageListResponse;
import com.chinarewards.gwt.elt.client.messageLattice.view.MessageLatticeWidget;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;

public class MessageListViewAdapter extends BaseDataProvider<MessageListClient> {

	final DispatchAsync dispatch;
	final MessageListDisplay display;
	MessageListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	public MessageListViewAdapter(DispatchAsync dispatch,
			MessageListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, MessageListDisplay display,Win win) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display = display;
		this.win=win;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<MessageListClient> list = new ArrayList<MessageListClient>();
		// for (int i = start; i < start + length; i++) {
		// MessageListClient item = new MessageListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(MessageListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchMessageListRequest(getCriteria(),
				sessionManager.getSession()),
				new AsyncCallback<SearchMessageListResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchMessageListResponse response) {
						List<MessageListClient> giftList = response.getResult();
						int numRows=response.getResult().size();
						Grid grid = new Grid(numRows, 1);

	
						for (int row = 0; row < numRows; row++) {
							MessageListClient clint = giftList.get(row);
							grid.setWidget(
									row,
									0,
									new MessageLatticeWidget(clint.getId(),
											clint.getStaffPhoto(), clint
													.getCreatedByUserName(),
											clint.getContent(),
											DateTool.dateToStringChina2(clint
													.getBroadcastingTime()),win,dispatch,sessionManager));
						}

						// Return the panel
						grid.ensureDebugId("cwGrid");

						display.getResultPanel().clear();
						display.getResultPanel().add(grid);
						display.setDataCount(response.getTotal() + "");
					}

				});
		// }
	}

	public void setCriteria(MessageListCriteria criteria) {
		this.criteria = criteria;
	}

	private MessageListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new MessageListCriteria();
		}

		return criteria;
	}
}
