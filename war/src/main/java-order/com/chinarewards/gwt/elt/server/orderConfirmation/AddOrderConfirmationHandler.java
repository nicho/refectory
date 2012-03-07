package com.chinarewards.gwt.elt.server.orderConfirmation;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.client.orderConfirmation.request.OrderConfirmationAddRequest;
import com.chinarewards.gwt.elt.client.orderConfirmation.request.OrderConfirmationAddResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 10:51:12
 */
public class AddOrderConfirmationHandler extends
		BaseActionHandler<OrderConfirmationAddRequest, OrderConfirmationAddResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;
	GiftService giftService;

	@Inject
	public AddOrderConfirmationHandler(OrderService orderService,GiftService giftService) {
		this.orderService = orderService;
		this.giftService=giftService;

	}

	@Override
	public OrderConfirmationAddResponse execute(OrderConfirmationAddRequest request,
			ExecutionContext context) throws DispatchException {

		OrderConfirmationAddResponse resp = new OrderConfirmationAddResponse();
		UserContext uc=new UserContext();
		uc.setUserId(request.getUserId());
		Orders order=new Orders();
		order.setGiftId(request.getGiftId());
		order.setAmount(request.getNumber());
		order.setIntegral(request.getTotal());
		order.setTel(request.getPhone());
		order.setAddress(request.getAddress());
		order.setPostcode(request.getZipCode());
		order.setReceiver(request.getName());
		order.setRemarks(request.getOrderDefinition());

		Orders addorder=orderService.save(uc, order);
		Gift gift=giftService.findGiftById(request.getGiftId());
		resp.setGiftId(addorder.getGiftId());
		resp.setGiftImage(gift.getPhoto());
		resp.setGiftName(gift.getName());
		resp.setName(addorder.getReceiver());
		resp.setPhone(addorder.getTel());
		resp.setAddress(addorder.getAddress());
		resp.setZipCode(addorder.getPostcode());
		resp.setOrderDefinition(addorder.getRemarks());
		resp.setIntegral(gift.getIntegral());
		resp.setNumber(addorder.getAmount());
		resp.setSource(gift.getSource());
		resp.setOrderId(addorder.getId());
		resp.setUserBalance(request.getUserBalance());
		resp.setBusiness(gift.getBusiness());
		resp.setServicetell(gift.getServicetell());
		resp.setTotal(request.getTotal());
		return resp;
	}

	
	 
	@Override
	public Class<OrderConfirmationAddRequest> getActionType() {
		return OrderConfirmationAddRequest.class;
	}

	@Override
	public void rollback(OrderConfirmationAddRequest req, OrderConfirmationAddResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
