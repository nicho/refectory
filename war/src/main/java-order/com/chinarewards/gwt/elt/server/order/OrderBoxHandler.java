package com.chinarewards.gwt.elt.server.order;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 10:51:12
 */
public class OrderBoxHandler extends
		BaseActionHandler<OrderBoxRequest, OrderBoxResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;

	@Inject
	public OrderBoxHandler(OrderService orderService) {
		this.orderService = orderService;

	}

	@Override
	public OrderBoxResponse execute(OrderBoxRequest request,
			ExecutionContext context) throws DispatchException {

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());
		int rs = 0;
		
		if(request.getStatus().trim().equals("INITIAL"))
			 rs = orderService.getOrderByStatus(uc,	OrderStatus.INITIAL);
		if(request.getStatus().trim().equals("NUSHIPMENTS"))
			 rs = orderService.getOrderByStatus(uc,	OrderStatus.NUSHIPMENTS);
		System.out.println("ddddddddddddd=========="+rs);
		return new OrderBoxResponse(rs);
	}

	@Override
	public Class<OrderBoxRequest> getActionType() {
		return OrderBoxRequest.class;
	}

	@Override
	public void rollback(OrderBoxRequest req, OrderBoxResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
