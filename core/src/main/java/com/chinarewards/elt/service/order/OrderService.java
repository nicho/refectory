package com.chinarewards.elt.service.order;

import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.OrderStatus;
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
	 * 未处理订单查询
	 * @param context
	 * @param Orders
	 * @return
	 */
	public PageStore<Orders> getUnhandledOrderList(UserContext context);
	
	/**
	 * 处理订单结果反馈
	 * @param context
	 * @param Orders
	 * @return
	 */
	public String processingOrdersResult(String orderId,OrderStatus status);
	
}
