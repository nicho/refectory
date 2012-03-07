/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.gift.GiftDao;
import com.chinarewards.elt.service.gift.GiftLogic;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.elt.service.gift.impl.GiftLogicImpl;
import com.chinarewards.elt.service.gift.impl.GiftServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * gift module.
 * 
 * @author nicho
 * @since 2012年1月10日 16:00:44
 */
public class GiftModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// gift bind
		bind(GiftDao.class).in(Singleton.class);

		bind(GiftLogic.class).to(GiftLogicImpl.class).in(Singleton.class);

		bind(GiftService.class).to(GiftServiceImpl.class).in(Singleton.class);
	}

}
