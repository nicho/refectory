package com.chinarewards.gwt.elt.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemStoreListPresenter.RewardsItemStoreListDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemStoreRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemStoreResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author lw
 * @since 2012-1-3
 */
public class RewardsItemStoreListViewAdapter extends	BaseDataProvider<RewardsItemClient> {

	final DispatchAsync dispatch;
	RewardsItemCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final RewardsItemStoreListDisplay display;

	public RewardsItemStoreListViewAdapter(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager, RewardsItemStoreListDisplay display) {
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	@Override
	public void fetchData(final int start, final int length) {
//		if (!GWT.isScript()) {
//			List<RewardsItemClient> list = new ArrayList<RewardsItemClient>();
//			for (int i = start; i < start + length; i++) {
//				RewardsItemClient item = new RewardsItemClient();
//				item.setName("rewards" + i);
//				RewardsTypeClient type = new RewardsTypeClient();
//				type.setId(i + "");
//				type.setName("name" + i);
//				item.setType(type);
//				item.setDefinition("行为定义" + i);
//				item.setStandard("标注哦" + i);
//				item.setCreatedBy("某某某");
//				item.setCreateAt(new Date());
//				list.add(item);
//			}
//
//			updateRowData(start, list);
//			updateRowCount(100, true);
//		} else
	//	{
			PaginationDetailClient pagination = new PaginationDetailClient();
			pagination.setStart(start);
			pagination.setLimit(length);
			getCriteria().setPagination(pagination);
			if (getSorting() != null) {
				getCriteria().setSorting(getSorting());
			}
			dispatch.execute(new SearchRewardsItemStoreRequest(getCriteria(),sessionManager.getSession()),
					new AsyncCallback<SearchRewardsItemStoreResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(SearchRewardsItemStoreResponse response) {
							updateRowData(start, response.getResult());
							updateRowCount(response.getTotal(), true);
							display.setDataCount(response.getTotal()+"");
						}

					});
		//}
	}

	public void setCriteria(RewardsItemCriteria criteria) {
		this.criteria = criteria;
	}

	public RewardsItemCriteria getCriteria() {
		if (criteria == null) {
			criteria = new RewardsItemCriteria();
		}
		return criteria;
	}
}
