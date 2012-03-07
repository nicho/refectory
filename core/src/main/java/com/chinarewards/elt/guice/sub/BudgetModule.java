/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.budget.CorpBudgetDao;
import com.chinarewards.elt.dao.budget.DepartmentBudgetDao;
import com.chinarewards.elt.service.budget.BudgetLogic;
import com.chinarewards.elt.service.budget.BudgetService;
import com.chinarewards.elt.service.budget.impl.BudgetLogicImpl;
import com.chinarewards.elt.service.budget.impl.BudgetServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * order module.
 * 
 * @author lw
 * @since 2012年1月10日 16:00:44
 */
public class BudgetModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		//  bind
		bind(CorpBudgetDao.class).in(Singleton.class);
		bind(DepartmentBudgetDao.class).in(Singleton.class);

		bind(BudgetLogic.class).to(BudgetLogicImpl.class).in(Singleton.class);

		bind(BudgetService.class).to(BudgetServiceImpl.class).in(Singleton.class);
	}

}
