package com.chinarewards.elt.service.order.impl;

import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.OrderListCriteria;
import com.chinarewards.elt.model.order.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderLogic;
import com.chinarewards.elt.service.order.OrderService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
@Transactional
public class OrderServiceImpl implements OrderService {
	private final OrderLogic orderLogic;

	@Inject
	public OrderServiceImpl(OrderLogic orderLogic) {
		
		this.orderLogic = orderLogic;

		
	}

	@Override
	public Orders save(UserContext context, Orders order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders findOrderById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrder(UserContext context, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageStore<Orders> getUnhandledOrderList(UserContext context) {
		OrderListCriteria criteria=new OrderListCriteria();
		criteria.setOrderStatus(OrderStatus.UNHANDLED);
		
		return orderLogic.getOrderList(context, criteria);
	}

	@Override
	public String processingOrdersResult(String orderId, OrderStatus status) {
		return orderLogic.processingOrdersResult(orderId, status);
	}


}
