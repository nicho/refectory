package com.chinarewards.gwt.elt.client.staff.module;


import com.chinarewards.gwt.elt.client.staff.presenter.HrRegisterPresenter;
import com.chinarewards.gwt.elt.client.staff.presenter.HrRegisterPresenter.HrRegisterDisplay;
import com.chinarewards.gwt.elt.client.staff.presenter.HrRegisterPresenterImpl;
import com.chinarewards.gwt.elt.client.staff.view.HrRegisterWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class HrRegisterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(HrRegisterPresenter.class).to(HrRegisterPresenterImpl.class);
		bind(HrRegisterDisplay.class).to(HrRegisterWidget.class);
	}

}
