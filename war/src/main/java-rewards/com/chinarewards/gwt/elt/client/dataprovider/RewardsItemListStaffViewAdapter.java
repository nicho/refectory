package com.chinarewards.gwt.elt.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListStaffPresenter.RewardsItemListStaffDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridRequest;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author yanrui
 */
public class RewardsItemListStaffViewAdapter extends
		BaseDataProvider<RewardsGridClient> {

	final DispatchAsync dispatch;
	RewardsGridCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final RewardsItemListStaffDisplay display;

	public RewardsItemListStaffViewAdapter(DispatchAsync dispatch,
			RewardsGridCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, RewardsItemListStaffDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display = display;
	}

	@Override
	public void fetchData(final int start, final int length) {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new SearchRewardsGridRequest(getCriteria(),
				sessionManager.getSession()),
				new AsyncCallback<SearchRewardsGridResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchRewardsGridResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal() + "");
					}
				});
	}

	public void setCriteria(RewardsGridCriteria criteria) {
		this.criteria = criteria;
	}

	public RewardsGridCriteria getCriteria() {
		if (criteria == null) {
			criteria = new RewardsGridCriteria();
		}
		return criteria;
	}
}
