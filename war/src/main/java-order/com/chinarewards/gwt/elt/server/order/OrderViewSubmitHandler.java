package com.chinarewards.gwt.elt.server.order;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.order.request.OrderViewRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderViewResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 10:51:12
 */
public class OrderViewSubmitHandler extends
		BaseActionHandler<OrderViewRequest, OrderViewResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;

	@Inject
	public OrderViewSubmitHandler(OrderService orderService) {
		this.orderService = orderService;

	}

	@Override
	public OrderViewResponse execute(OrderViewRequest request,
			ExecutionContext context) throws DispatchException {

		UserContext uc = new UserContext();
		uc.setUserId(request.getUserId());
		String rs = "fail";
		if(request.getStauts().trim().equals("SHIPMENTS"))
		 rs = orderService.updateStatus(uc, request.getOrderId(),	OrderStatus.SHIPMENTS);
		if(request.getStauts().trim().equals("ERRORORDER"))
			 rs = orderService.updateStatus(uc, request.getOrderId(),	OrderStatus.ERRORORDER);
		return new OrderViewResponse(rs);
	}

	@Override
	public Class<OrderViewRequest> getActionType() {
		return OrderViewRequest.class;
	}

	@Override
	public void rollback(OrderViewRequest req, OrderViewResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
