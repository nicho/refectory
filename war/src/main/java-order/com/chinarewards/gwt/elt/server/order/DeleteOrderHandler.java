package com.chinarewards.gwt.elt.server.order;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.order.request.DeleteOrderRequest;
import com.chinarewards.gwt.elt.client.order.request.DeleteOrderResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DeleteOrderHandler extends
		BaseActionHandler<DeleteOrderRequest, DeleteOrderResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;

	@Inject
	public DeleteOrderHandler(OrderService orderService) {
		this.orderService = orderService;

	}

	@Override
	public DeleteOrderResponse execute(DeleteOrderRequest request,
			ExecutionContext context) throws DispatchException {
		
		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());

		String totle = orderService.deleteOrder(uc,request.getOrderId());		
		
		return new DeleteOrderResponse(totle);
	}

	
	@Override
	public Class<DeleteOrderRequest> getActionType() {
		return DeleteOrderRequest.class;
	}

	@Override
	public void rollback(DeleteOrderRequest req, DeleteOrderResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
