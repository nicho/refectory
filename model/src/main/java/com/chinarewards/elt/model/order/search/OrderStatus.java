package com.chinarewards.elt.model.order.search;


public  enum OrderStatus {

	/* 初始时，为没付积分 */
	 INITIAL,
	  
	/* 待发货 */	
	NUSHIPMENTS,
	
	/* 已发货 */	
	SHIPMENTS,
	
	/* 确认发货 */	
	AFFIRM,
	
	/* 问题订单 */
	ERRORORDER;
}

