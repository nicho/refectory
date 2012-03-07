package com.chinarewards.gwt.elt.server.orderHistory;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewRequest;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class OrderHistoryViewHandler extends
		BaseActionHandler<OrderHistoryViewRequest, OrderHistoryViewResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;	

	@Inject
	public OrderHistoryViewHandler(OrderService orderService) {
		this.orderService = orderService;		
	}

	@Override
	public OrderHistoryViewResponse execute(OrderHistoryViewRequest request,ExecutionContext context) throws DispatchException {
		UserContext uc = new UserContext();
		uc.setUserId(request.getUserId());
		String rs = "fail";
		if(request.getStauts().trim().equals("NUSHIPMENTS"))//付积分待发货
		     rs = orderService.updateStatus(uc, request.getOrderId(),	OrderStatus.NUSHIPMENTS);
		if(request.getStauts().trim().equals("AFFIRM"))//确认收货
			 rs = orderService.updateStatus(uc, request.getOrderId(),	OrderStatus.AFFIRM);
		return new OrderHistoryViewResponse(rs);
	}

	@Override
	public Class<OrderHistoryViewRequest> getActionType() {
		return OrderHistoryViewRequest.class;
	}

	@Override
	public void rollback(OrderHistoryViewRequest arg0,
			OrderHistoryViewResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}
}
