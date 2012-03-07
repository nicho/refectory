package com.chinarewards.gwt.elt.client.staffIntegral.module;


import com.chinarewards.gwt.elt.client.staffIntegral.presenter.StaffIntegralPresenter;
import com.chinarewards.gwt.elt.client.staffIntegral.presenter.StaffIntegralPresenter.StaffIntegralDisplay;
import com.chinarewards.gwt.elt.client.staffIntegral.presenter.StaffIntegralPresenterImpl;
import com.chinarewards.gwt.elt.client.staffIntegral.view.StaffIntegralWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class StaffIntegralModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffIntegralPresenter.class).to(StaffIntegralPresenterImpl.class);
		bind(StaffIntegralDisplay.class).to(StaffIntegralWidget.class);
	}

}
