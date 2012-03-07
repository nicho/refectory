package com.chinarewards.gwt.elt.client.staffHeavenIndex.module;


import com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter.StaffHeavenIndexPresenter;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter.StaffHeavenIndexPresenter.StaffHeavenIndexDisplay;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter.StaffHeavenIndexPresenterImpl;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.view.StaffHeavenIndexWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class StaffHeavenIndexModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffHeavenIndexPresenter.class).to(StaffHeavenIndexPresenterImpl.class);
		bind(StaffHeavenIndexDisplay.class).to(StaffHeavenIndexWidget.class);
	}

}
