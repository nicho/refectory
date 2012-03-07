package com.chinarewards.gwt.elt.client.staffAdd.module;


import com.chinarewards.gwt.elt.client.staffAdd.presenter.StaffAddPresenter;
import com.chinarewards.gwt.elt.client.staffAdd.presenter.StaffAddPresenter.StaffAddDisplay;
import com.chinarewards.gwt.elt.client.staffAdd.presenter.StaffAddPresenterImpl;
import com.chinarewards.gwt.elt.client.staffAdd.view.StaffAddWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class StaffAddModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffAddPresenter.class).to(StaffAddPresenterImpl.class);
		bind(StaffAddDisplay.class).to(StaffAddWidget.class);
	}

}
