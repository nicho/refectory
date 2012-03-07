package com.chinarewards.elt.service.order;

import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.order.search.OrderListVo;

public interface OrderLogic {
	/**
	 * 保存
	 * @param context
	 * @param Order
	 * @return
	 */
	public Orders save(SysUser caller, Orders Order);

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
	public String deleteOrder(SysUser caller,String id);
	/**
	 * 订单列表
	 * @param context
	 * @param Orders
	 * @return
	 */
	public PageStore<OrderListVo> OrderList(SysUser caller,OrderListVo OrderVo);

	/**
	 * 执行状态
	 * @param id
	 * @return
	 */
	public String updateStatus(SysUser caller,String id,OrderStatus status);
	
	
	public int getOrderByStatus(OrderListVo orderVo);
}


