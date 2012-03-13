package com.chinarewards.gwt.elt.client.userList.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.userList.model.UserListClient;
import com.chinarewards.gwt.elt.client.userList.model.UserListCriteria;
import com.chinarewards.gwt.elt.client.userList.presenter.UserListPresenter.UserListDisplay;
import com.chinarewards.gwt.elt.client.userList.request.SearchUserListRequest;
import com.chinarewards.gwt.elt.client.userList.request.SearchUserListResponse;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserListViewAdapter extends BaseDataProvider<UserListClient> {

	final DispatchAsync dispatch;
	final UserListDisplay display;
	UserListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public UserListViewAdapter(DispatchAsync dispatch,
			UserListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, UserListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<UserListClient> list = new ArrayList<UserListClient>();
		// for (int i = start; i < start + length; i++) {
		// UserListClient item = new UserListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(UserListStatus.TO_BE_ISSUE);
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
