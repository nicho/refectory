package com.chinarewards.gwt.elt.client.staffHeavenIndex.dataprovider;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcastReplyLattice.view.BroadcastReplyLatticeWidget;
import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexClient;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexCriteria;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter.StaffHeavenIndexPresenter.StaffHeavenIndexDisplay;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.request.StaffHeavenIndexRequest;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.request.StaffHeavenIndexResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;

public class StaffHeavenIndexViewAdapter extends
		BaseDataProvider<StaffHeavenIndexClient> {

	final DispatchAsync dispatch;
	final StaffHeavenIndexDisplay display;
	StaffHeavenIndexCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	public StaffHeavenIndexViewAdapter(DispatchAsync dispatch,
			StaffHeavenIndexCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, StaffHeavenIndexDisplay display,
			Win win) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display = display;
		this.win = win;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<StaffHeavenIndexClient> list = new
		// ArrayList<StaffHeavenIndexClient>();
		// for (int i = start; i < start + length; i++) {
		// StaffHeavenIndexClient item = new StaffHeavenIndexClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(StaffHeavenIndexStatus.TO_BE_ISSUE);
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
		dispatch.execute(new StaffHeavenIndexRequest(getCriteria(),
				sessionManager.getSession()),
				new AsyncCallback<StaffHeavenIndexResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(StaffHeavenIndexResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal() + "");

						List<StaffHeavenIndexClient> giftList = response
								.getResult();
						int index = 0;
						int tol = 10;
						if (response.getResult().size() < tol)
							tol = response.getResult().size();
						Grid grid = new Grid(tol, 1);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									StaffHeavenIndexClient clint = giftList
											.get(index);
									grid.setWidget(
											row,
											col,
											new BroadcastReplyLatticeWidget(
													win,
													dispatch,
													sessionManager,
													clint.getId(),
													null,
													null,
													clint.getContent(),
													DateTool.dateToStringChina2(clint
															.getBroadcastingTime()),
													clint.getCreatedByUserName(),
													clint.getReplyNumber(),
													clint.isAllowreplies())
													.asWidget());
									index++;
								} else {
									break;
									// grid.setWidget(row, col,new
									// BroadcastReplyLatticeWidget().asWidget());
								}
							}
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

	public void setCriteria(StaffHeavenIndexCriteria criteria) {
		this.criteria = criteria;
	}

	private StaffHeavenIndexCriteria getCriteria() {
		if (criteria == null) {
			criteria = new StaffHeavenIndexCriteria();
		}

		return criteria;
	}
}
