package com.chinarewards.gwt.elt.client.colleague.dataprovider;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.colleague.presenter.ColleagueListPresenter.ColleagueListDisplay;
import com.chinarewards.gwt.elt.client.colleagueLattice.view.ColleagueLatticeWidget;
import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.messageSave.dialog.MessageSaveDialog;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListRequest;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.inject.Provider;

public class ColleagueListViewAdapter extends BaseDataProvider<StaffListClient> {

	final DispatchAsync dispatch;
	final ColleagueListDisplay display;
	StaffListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	 Provider<MessageSaveDialog> messageSaveDialog;
	public ColleagueListViewAdapter(DispatchAsync dispatch,
			StaffListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, ColleagueListDisplay display, Provider<MessageSaveDialog> messageSaveDialog) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display = display;
		this.messageSaveDialog=messageSaveDialog;
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
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		getCriteria().setColleaguePage(true);
		dispatch.execute(new SearchStaffListRequest(getCriteria(),
				sessionManager.getSession()),
				new AsyncCallback<SearchStaffListResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchStaffListResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal() + "");

						List<StaffListClient> staffList = response.getResult();
						int index = 0;

						Grid grid = new Grid(5, 3);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < staffList.size()) {
									StaffListClient clint = staffList.get(index);
									if(clint.getDepartmentName().indexOf("ROOT")!=-1)
										clint.setDepartmentName("");
									grid.setWidget(row, col,
											new ColleagueLatticeWidget(clint.getStaffId(),clint.getStaffName(),clint.getDepartmentName(),clint.getPhoto(),messageSaveDialog));
									index++;
								} else {
									break;
//									grid.setWidget(row, col,
//											new ColleagueLatticeWidget(null,null,null,null));
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
