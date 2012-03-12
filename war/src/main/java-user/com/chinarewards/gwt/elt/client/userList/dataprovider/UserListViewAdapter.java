package com.chinarewards.gwt.elt.client.userList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter.StaffListDisplay;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListRequest;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserListViewAdapter extends BaseDataProvider<UserListClient> {

	final DispatchAsync dispatch;
	final StaffListDisplay display;
	UserListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public UserListViewAdapter(DispatchAsync dispatch,
			UserListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, StaffListDisplay display) {
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
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new SearchUserListRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchUserListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchUserListResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
			}

		});
		// }
	}


	public void setCriteria(UserListCriteria criteria) {
		this.criteria = criteria;
	}

	private UserListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new UserListCriteria();
		}

		return criteria;
	}
}
