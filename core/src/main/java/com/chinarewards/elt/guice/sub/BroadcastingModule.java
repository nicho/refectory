package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.broadcast.BroadcastDao;
import com.chinarewards.elt.dao.broadcast.BroadcastReplyDao;
import com.chinarewards.elt.dao.broadcast.BroadcastingReceivingDao;
import com.chinarewards.elt.dao.broadcast.DepartmentObjectDao;
import com.chinarewards.elt.dao.broadcast.OrgObjectDao;
import com.chinarewards.elt.dao.broadcast.ReceivingObjectDao;
import com.chinarewards.elt.dao.broadcast.StaffObjectDao;
import com.chinarewards.elt.dao.broadcast.TeamObjectDao;
import com.chinarewards.elt.service.broadcast.BroadcastLogic;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.elt.service.broadcast.impl.BroadcastLogicImpl;
import com.chinarewards.elt.service.broadcast.impl.BroadcastServiceImpl;
import com.google.inject.AbstractModule;

public class BroadcastingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BroadcastDao.class);
		bind(BroadcastingReceivingDao.class);
		bind(ReceivingObjectDao.class);
		bind(DepartmentObjectDao.class);
		bind(OrgObjectDao.class);
		bind(StaffObjectDao.class);
		bind(TeamObjectDao.class);
		bind(BroadcastReplyDao.class);
		bind(BroadcastLogic.class).to(BroadcastLogicImpl.class);
		bind(BroadcastService.class).to(BroadcastServiceImpl.class);
	}

}