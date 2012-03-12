package com.chinarewards.elt.service.order.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.order.OrderDao;
import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.OrderListCriteria;
import com.chinarewards.elt.model.order.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

public class OrderLogicImpl implements OrderLogic{
	private OrderDao orderDao;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Inject
	protected OrderLogicImpl(OrderDao orderDao){
		this.orderDao = orderDao;

	}
	
	@Override
	public Orders save(SysUser caller, Orders order) {
		Date currTime = DateUtil.getTime();
		String millsTime=currTime.getTime()+"";
		String orderCode = DateUtil.formatData("yyyyMMddhhmmss", currTime)+millsTime.substring(millsTime.length()-3);
		if (StringUtil.isEmptyString(order.getId())) {
			// Create
			order.setDeleted(false);//正常状态，没有删除为0
			order.setCode(orderCode);//用当前时间作为订单编号
			orderDao.save(order);
		} else {
			// Update
			order = orderDao.findById(Orders.class, order.getId());

			orderDao.update(order);
		}

		return order;
	}
	

	@Override
	public Orders findOrderById(String id) {
		
		return  orderDao.findById(Orders.class, id);
	}

	@Override
	public String deleteOrder(SysUser caller,String id) {
	
		Orders order = orderDao.findById(Orders.class, id);
		order.setDeleted(true);

		order= orderDao.update(order);
		return order.getId();
	}
//
//	@Override
//	public PageStore<OrderListVo> OrderList(SysUser caller, OrderListVo orderVo) {
//		
//		PageStore<Orders> pageStore = new PageStore<Orders>();
//		
//		pageStore.setResultCount(orderDao.countOrder(orderVo));
////		List<Orders> OrderList = orderDao.OrderList(orderVo);
////		List<OrderListVo> OrderVoList = new ArrayList<OrderListVo>();
////		for (Orders order : OrderList) {
////			OrderVoList.add(convertFromOrderToVo(order));
////		}
//		PageStore<OrderListVo> storeVo = new PageStore<OrderListVo>();
//		storeVo.setResultCount(pageStore.getResultCount());
////		storeVo.setResultList(OrderVoList);
//
//		return storeVo;
//	}
//	
//	@Override
//	public String updateStatus(SysUser caller,String id,OrderStatus status) {
////		Date currTime = DateUtil.getTime();
////		Orders order = orderDao.findById(Orders.class, id);
////		order.setStatus(status);
////		order.setRecorddate(currTime);
////		order.setRecorduser(caller.getUserName());
////		if(status.equals(OrderStatus.ERRORORDER))
////			order.setRemarks(order.getRemarks()+"（退回备注：被"+caller.getUserName()+"在"+DateUtil.formatData("", currTime)+"退回）");
////		
////		order= orderDao.update(order);
//		return null;
//	}
//
//	@Override
//	public int getOrderByStatus(OrderListVo orderVo){
//		return orderDao.countOrder( orderVo);
//	}

	@Override
	public PageStore<Orders> getOrderList(UserContext context,
			OrderListCriteria criteria) {
		return orderDao.queryOrderPageAction(criteria);
	}

	@Override
	public String processingOrdersResult(String orderId, OrderStatus status) {
		Orders order=orderDao.findByOrdersId(orderId);
		if(order!=null && status!=null)
		{
			order.setOrderStatus(status);
			orderDao.update(order);
			return "SUCCESS";
		}
		else
		{
			return "FAILURE";
		}
	}
}
