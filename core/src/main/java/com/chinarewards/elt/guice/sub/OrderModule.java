/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.order.OrderDao;
import com.chinarewards.elt.service.order.OrderLogic;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.elt.service.order.impl.OrderLogicImpl;
import com.chinarewards.elt.service.order.impl.OrderServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * order module.
 * 
 * @author nicho
 * @since 2012年1月10日 16:00:44
 */
public class OrderModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// order bind
		bind(OrderDao.class).in(Singleton.class);

		bind(OrderLogic.class).to(OrderLogicImpl.class).in(Singleton.class);

		bind(OrderService.class).to(OrderServiceImpl.class).in(Singleton.class);
	}

}
