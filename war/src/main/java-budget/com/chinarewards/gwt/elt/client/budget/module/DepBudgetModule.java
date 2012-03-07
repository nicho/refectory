package com.chinarewards.gwt.elt.client.budget.module;

import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenter;
import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenter.CreateBudgetDisplay;
import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenterImpl;
import com.chinarewards.gwt.elt.client.budget.view.CreateBudgetWidget;
import com.google.gwt.inject.client.AbstractGinModule;

/**
 * 
 * @author harry
 * @since 2010-12-16
 */
public class DepBudgetModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(CreateBudgetPresenter.class).to(CreateBudgetPresenterImpl.class);
		bind(CreateBudgetDisplay.class).to(CreateBudgetWidget.class);

		
				

	}

}
