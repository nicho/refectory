package com.chinarewards.gwt.elt.client.staffInfo.module;


import com.chinarewards.gwt.elt.client.staffInfo.presenter.StaffInfoPresenter;
import com.chinarewards.gwt.elt.client.staffInfo.presenter.StaffInfoPresenter.StaffInfoDisplay;
import com.chinarewards.gwt.elt.client.staffInfo.presenter.StaffInfoPresenterImpl;
import com.chinarewards.gwt.elt.client.staffInfo.view.StaffInfoWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class StaffInfoModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffInfoPresenter.class).to(StaffInfoPresenterImpl.class);
		bind(StaffInfoDisplay.class).to(StaffInfoWidget.class);
	}

}
