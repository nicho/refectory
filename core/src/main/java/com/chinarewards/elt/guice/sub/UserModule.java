/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.elt.service.user.impl.UserLogicImpl;
import com.chinarewards.elt.service.user.impl.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * User module.
 * 
 * @author nicho
 * @since 2012年1月10日 16:00:44
 */
public class UserModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// User bind
		bind(UserDao.class).in(Singleton.class);

		bind(UserLogic.class).to(UserLogicImpl.class).in(Singleton.class);

		bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
	}

}
