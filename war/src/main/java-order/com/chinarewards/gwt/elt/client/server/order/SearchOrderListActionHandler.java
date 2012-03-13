package com.chinarewards.gwt.elt.client.server.order;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.OrderListCriteria;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.orderList.model.OrderListClient;
import com.chinarewards.gwt.elt.client.orderList.request.SearchOrderListRequest;
import com.chinarewards.gwt.elt.client.orderList.request.SearchOrderListResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;

public class SearchOrderListActionHandler extends
		BaseActionHandler<SearchOrderListRequest, SearchOrderListResponse> {

	OrderService OrderService;
	
	@Inject
	public SearchOrderListActionHandler(OrderService OrderService)
	{
		this.OrderService=OrderService;
	}
	public SearchOrderListActionHandler() {

	}
	@Override
	public Class<SearchOrderListRequest> getActionType() {
		return SearchOrderListRequest.class;
	}

	@Override
	public SearchOrderListResponse execute(SearchOrderListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchOrderListResponse rep=new SearchOrderListResponse();
		UserContext u=new UserContext();
		OrderListCriteria criteria=new OrderListCriteria();
		PageStore<Orders> Orderpage= OrderService.getOrderList(u, criteria);
		rep.setTotal(Orderpage.getResultCount());
		
		List<OrderListClient> result=new ArrayList<OrderListClient>();
		if(Orderpage.getResultList()!=null && Orderpage.getResultList().size()>=0)
		{
			for (Orders order:Orderpage.getResultList()) {
				OrderListClient r=new OrderListClient();
				r.setStaffName(order.getCode());
				result.add(r);
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchOrderListRequest action, SearchOrderListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
