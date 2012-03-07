package com.chinarewards.gwt.elt.client.team.dataprovider;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenter.TeamListDisplay;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamRequest;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamResponse;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TeamListViewAdapter extends BaseDataProvider<TeamSearchVo> {

	final DispatchAsync dispatch;
	final TeamSearchVo criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final TeamListDisplay display;

	public TeamListViewAdapter(DispatchAsync dispatch, TeamSearchVo criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, TeamListDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<GiftClient> list = new ArrayList<GiftClient>();
		// for (int i = start; i < start + length; i++) {
		// GiftClient item = new GiftClient();
		// item.setId("id" + i);
		// item.setName("gift" + i);
		// item.setSource("来源"+i);
		// item.setStatus(GiftStatus.SHELF);
		// list.add(item);
		// }
		//
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		criteria.setPagination(pagination);
		if (getSorting() != null) {
			criteria.setSorting(getSorting());
		}
		dispatch.execute(new SearchTeamRequest(criteria, sessionManager.getSession()),
				new AsyncCallback<SearchTeamResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchTeamResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						display.setDataCount(response.getTotal()+"");
					}

				});
	}
	// }

}
