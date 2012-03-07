package com.chinarewards.gwt.elt.client.broadcastReply.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListClient;
import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListCriteria;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyResponse;
import com.chinarewards.gwt.elt.client.broadcastReplyLattice.view.ReplyLatticeWidget;
import com.chinarewards.gwt.elt.client.dataprovider.BaseDataProvider;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter.DetailsOfBroadcastPresenter.DetailsOfBroadcastDisplay;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;

public class ReplyListViewAdapter extends BaseDataProvider<ReplyListClient> {

	final DispatchAsync dispatch;
	final DetailsOfBroadcastDisplay display;
	ReplyListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public ReplyListViewAdapter(DispatchAsync dispatch,
			ReplyListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, DetailsOfBroadcastDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<BroadcastingListClient> list = new ArrayList<BroadcastingListClient>();
		// for (int i = start; i < start + length; i++) {
		// BroadcastingListClient item = new BroadcastingListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(BroadcastingListStatus.TO_BE_ISSUE);
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
		dispatch.execute(new SearchBroadcastReplyRequest(getCriteria(), sessionManager
				.getSession()), new AsyncCallback<SearchBroadcastReplyResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchBroadcastReplyResponse response) {
				updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
				display.setDataCount(response.getTotal()+"");
				
				List<ReplyListClient> giftList=response.getResult();
				List<ReplyListClient> replyList = new ArrayList<ReplyListClient>();
				
				
				Map<String,List<ReplyListClient>> map=new HashMap<String,List<ReplyListClient>>();
				
				for (int i = 0; i < giftList.size(); i++) {
					ReplyListClient reply=giftList.get(i);
					if(StringUtil.isEmpty(reply.getParent()))
					{
						replyList.add(reply);
					}
					else
					{
						List<ReplyListClient> parentClient=map.get(reply.getParent());
						if(parentClient==null)
							parentClient=new ArrayList<ReplyListClient>();
						parentClient.add(reply);
						map.put(reply.getParent(), parentClient);
					}
				}
				
				
				int index=0;
				int tol=10;
				if(response.getResult().size()<tol)
					tol=response.getResult().size();
				 Grid grid = new Grid(tol, 1);
					
				    // Add images to the grid
				    int numRows = grid.getRowCount();
				    int numColumns = grid.getColumnCount();
				    for (int row = 0; row < numRows; row++) {
				      for (int col = 0; col < numColumns; col++) {
				    	  if(index<replyList.size())	
				    	  {
				    		  ReplyListClient clint=replyList.get(index);
								grid.setWidget(
										row,
										col,
										new ReplyLatticeWidget(
												null,
												clint.getId(),
												clint.getReplyUserPhoto(),
												clint.getReplyUserName(),
												clint.getReplyContent(),
												DateTool.dateToStringChina2(clint
														.getReplyTime()),map)
												.asWidget());
								index++;
				    	  }
				    	  else
				    	  {
				    		  break;
				    		  //grid.setWidget(row, col,new BroadcastReplyLatticeWidget().asWidget());
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
		// }
	}


	public void setCriteria(ReplyListCriteria criteria) {
		this.criteria = criteria;
	}

	private ReplyListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new ReplyListCriteria();
		}

		return criteria;
	}
}
