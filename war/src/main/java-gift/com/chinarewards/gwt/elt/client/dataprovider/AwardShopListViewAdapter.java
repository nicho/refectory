package com.chinarewards.gwt.elt.client.dataprovider;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.awardShop.presenter.AwardShopListPresenter.AwardShopListDisplay;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopRequest;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopResponse;
import com.chinarewards.gwt.elt.client.awardShopLattice.view.AwardShopLatticeWidget;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;

public class AwardShopListViewAdapter extends BaseDataProvider<GiftClient> {

	final DispatchAsync dispatch;
	final GiftCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final AwardShopListDisplay display;

	public AwardShopListViewAdapter(DispatchAsync dispatch, GiftCriteria criteria,
			ErrorHandler errorHandler, SessionManager sessionManager, AwardShopListDisplay display) {
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
		dispatch.execute(new SearchAwardShopRequest(criteria, sessionManager
				.getSession().getCorporationId(), sessionManager.getSession()
				.getUserRoles(), sessionManager.getSession().getToken()),
				new AsyncCallback<SearchAwardShopResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchAwardShopResponse response) {
						updateRowData(start, response.getResult());
						updateRowCount(response.getTotal(), true);
						
						List<GiftClient> giftList=response.getResult();
						int index=0;
						 Grid grid = new Grid(3, 4);
							
						    // Add images to the grid
						    int numRows = grid.getRowCount();
						    int numColumns = grid.getColumnCount();
						    for (int row = 0; row < numRows; row++) {
						      for (int col = 0; col < numColumns; col++) {
						    	  if(index<giftList.size())	
						    	  {
						    		  GiftClient clint=giftList.get(index);
						    		  grid.setWidget(row, col,new AwardShopLatticeWidget(clint.getName(),clint.getIntegral()+"",DateTool.dateToString(clint.getIndate())+"",clint.getPhoto(),clint.getId()).asWidget());
						    	  	  index++;
						    	  }
						    	  else
						    	  {
						    		  grid.setWidget(row, col,new AwardShopLatticeWidget("无数据","无数据","无数据","无数据",null).asWidget());
						    	  }
						      }
						    }

						    // Return the panel
						    grid.ensureDebugId("cwGrid");
						    
							display.getResultPanel().clear();
							display.getResultPanel().add(grid);
						display.setDataCount(response.getTotal()+"");
					}

				});
	}
	// }

}
