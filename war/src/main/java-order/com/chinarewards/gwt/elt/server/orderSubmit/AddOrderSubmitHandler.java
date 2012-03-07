package com.chinarewards.gwt.elt.server.orderSubmit;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.orderSubmit.request.OrderSubmitRequest;
import com.chinarewards.gwt.elt.client.orderSubmit.request.OrderSubmitResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 10:51:12
 */
public class AddOrderSubmitHandler extends
		BaseActionHandler<OrderSubmitRequest, OrderSubmitResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;

	@Inject
	public AddOrderSubmitHandler(OrderService orderService,
			GiftService giftService) {
		this.orderService = orderService;

	}

	@Override
	public OrderSubmitResponse execute(OrderSubmitRequest request,
			ExecutionContext context) throws DispatchException {

		UserContext uc = new UserContext();
		uc.setUserId(request.getUserId());
		String rs = orderService.updateStatus(uc, request.getOrderId(),
				OrderStatus.NUSHIPMENTS);
		return new OrderSubmitResponse(rs);
	}

	@Override
	public Class<OrderSubmitRequest> getActionType() {
		return OrderSubmitRequest.class;
	}

	@Override
	public void rollback(OrderSubmitRequest req, OrderSubmitResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
