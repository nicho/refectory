package com.chinarewards.gwt.elt.client.staffView.module;


import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenter;
import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenter.StaffViewDisplay;
import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenterImpl;
import com.chinarewards.gwt.elt.client.staffView.view.StaffViewWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class StaffViewModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffViewPresenter.class).to(StaffViewPresenterImpl.class);
		bind(StaffViewDisplay.class).to(StaffViewWidget.class);
	}

}
