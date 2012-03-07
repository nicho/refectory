package com.chinarewards.gwt.elt.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.chooseStaff.request.SearchStaffChooseRequest;
import com.chinarewards.gwt.elt.client.chooseStaff.request.SearchStaffChooseResponse;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffSearchCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StaffChooseAsyncDataProvider extends BaseDataProvider<StaffClient> {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final StaffSearchCriteria criteria;
	final boolean filterByAcl;

	public StaffChooseAsyncDataProvider(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			StaffSearchCriteria criteria, boolean filterByAcl) {
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.criteria = criteria;
		this.filterByAcl = filterByAcl;
	}

	@Override
	public void fetchData(final int start, final int length) {
//		if (false) {
//			List<StaffClient> list = new ArrayList<StaffClient>();
//			for (int i = start; i < start + length; i++) {
//				list.add(new StaffClient("" + i,
//						criteria.getKey() == null ? "name" : criteria.getKey()
//								+ i, "cardNo" + i, "deptName" + i, "email" + i));
//			}
//			updateRowData(start, list);
//			updateRowCount(100, true);
//		} else {
			PaginationDetailClient pagination = new PaginationDetailClient();
			pagination.setStart(start);
			pagination.setLimit(length);
			criteria.setPagination(pagination);
			criteria.setSorting(getSorting());
			dispatch.execute(new SearchStaffChooseRequest(criteria,	sessionManager.getSession(), filterByAcl),
					new AsyncCallback<SearchStaffChooseResponse>() {

						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(SearchStaffChooseResponse response) {
							updateRowData(start, response.getResult().getResult());
							updateRowCount(response.getResult().getTotal(),true);
						}
					});
		}
//	}

}
