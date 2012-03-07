package com.chinarewards.gwt.elt.client.integralManagement.module;


import com.chinarewards.gwt.elt.client.integralManagement.presenter.IntegralManagementPresenter;
import com.chinarewards.gwt.elt.client.integralManagement.presenter.IntegralManagementPresenter.IntegralManagementDisplay;
import com.chinarewards.gwt.elt.client.integralManagement.presenter.IntegralManagementPresenterImpl;
import com.chinarewards.gwt.elt.client.integralManagement.view.IntegralManagementWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class IntegralManagementModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(IntegralManagementPresenter.class).to(IntegralManagementPresenterImpl.class);
		bind(IntegralManagementDisplay.class).to(IntegralManagementWidget.class);
	}

}
