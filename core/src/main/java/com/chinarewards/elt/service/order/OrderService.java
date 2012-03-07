package com.chinarewards.elt.service.order;

import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.search.OrderListVo;
import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface OrderService {

	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public Orders save(UserContext context, Orders order);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Orders findOrderById(String id);
	
	
	/**
	 * 删除订单根据ID
	 * @param id
	 * @return
	 */
	public String deleteOrder(UserContext context,String id);
	/**
	 * 订单列表
	 * @param context
	 * @param Orders
	 * @return
	 */
	public PageStore<OrderListVo> OrderList(UserContext context,OrderListVo OrderVo);
   /**
    * 订单历史
    */
	PageStore<OrderListVo> OrderHistoryList(UserContext context, OrderListVo OrderVo);
	/**
	 * 执行状态改变
	 * @param orderId 
	 * @return  如果返回值为ok是成功，如果是fail是失败
	 */
	public String updateStatus(UserContext context,String orderId,OrderStatus status);
   /**
    * 根据礼品的积分来查找个人积分够不够
    * @param context
    * @param totalPrice  同一礼品的总积分
    * @return fasle 积分不够，true可以兑换
    */
	public boolean getIntegral(UserContext context,double totalPrice);
	/*
	 * 查找定单根据状态
	 */
	public int  getOrderByStatus(UserContext context,OrderStatus status);
}
