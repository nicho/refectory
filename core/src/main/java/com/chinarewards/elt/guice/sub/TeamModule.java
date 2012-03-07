/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.org.MembersDao;
import com.chinarewards.elt.dao.org.TeamDao;
import com.chinarewards.elt.service.org.TeamLogic;
import com.chinarewards.elt.service.org.TeamService;
import com.chinarewards.elt.service.org.impl.TeamLogicImpl;
import com.chinarewards.elt.service.org.impl.TeamServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * order module.
 * 
 * @author lw
 * @since 2012年2月10日 16:00:44
 */
public class TeamModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// order bind
		bind(TeamDao.class).in(Singleton.class);
		bind(MembersDao.class).in(Singleton.class);

		bind(TeamLogic.class).to(TeamLogicImpl.class).in(Singleton.class);

		bind(TeamService.class).to(TeamServiceImpl.class).in(Singleton.class);
	}

}
