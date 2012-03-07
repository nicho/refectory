package com.chinarewards.elt.guice;

import com.chinarewards.elt.common.BaseDao;
import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class CommonModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("elt"));
//		 EntityManager em = Persistence.createEntityManagerFactory("elt")
//		 .createEntityManager();
//		 bind(EntityManager.class).toInstance(em);
		bind(BaseDao.class);
	}

}
