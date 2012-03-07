package com.chinarewards.gwt.elt.client.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListPresenter.RewardsItemListDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Cream
 * @since 2010-12-23
 */
public class RewardsItemListViewAdapter extends	BaseDataProvider<RewardsItemClient> {

	final DispatchAsync dispatch;
	RewardsItemCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final RewardsItemListDisplay display;

	public RewardsItemListViewAdapter(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager, RewardsItemListDisplay display) {
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
			dispatch.execute(new SearchRewardsItemRequest(getCriteria(),sessionManager.getSession()),
					new AsyncCallback<SearchRewardsItemResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(SearchRewardsItemResponse response) {
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
