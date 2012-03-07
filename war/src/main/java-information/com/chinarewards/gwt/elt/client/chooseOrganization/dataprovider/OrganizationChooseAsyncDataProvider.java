package com.chinarewards.gwt.elt.client.chooseOrganization.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria;
import com.chinarewards.gwt.elt.client.chooseOrganization.request.ChooseOrganizationRequest;
import com.chinarewards.gwt.elt.client.chooseOrganization.request.ChooseOrganizationResponse;
import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class OrganizationChooseAsyncDataProvider extends BaseDataProvider<OrganicationClient> {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final OrganSearchCriteria criteria;
	final boolean filterByAcl;

	public OrganizationChooseAsyncDataProvider(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			OrganSearchCriteria criteria, boolean filterByAcl) {
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.criteria = criteria;
		this.filterByAcl = filterByAcl;
	}

	@Override
	public void fetchData(final int start, final int length) {
//		if (false) {
//			List<OrganicationClient> list = new ArrayList<OrganicationClient>();
//			for (int i = start; i < start + length; i++) {
//				list.add(new OrganicationClient("" + i,
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
			dispatch.execute(new ChooseOrganizationRequest(criteria,	sessionManager.getSession(), filterByAcl),
					new AsyncCallback<ChooseOrganizationResponse>() {

						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(ChooseOrganizationResponse response) {
							updateRowData(start, response.getResult().getResult());
							updateRowCount(response.getResult().getTotal(),true);
						}
					});
		}
//	}

}
