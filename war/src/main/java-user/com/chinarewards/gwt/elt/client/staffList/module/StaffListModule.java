package com.chinarewards.gwt.elt.client.staffList.module;


import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter.StaffListDisplay;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenterImpl;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPrintPresenter;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPrintPresenter.StaffListPrintDisplay;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPrintPresenterImpl;
import com.chinarewards.gwt.elt.client.staffList.view.StaffListPrintWidget;
import com.chinarewards.gwt.elt.client.staffList.view.StaffListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class StaffListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffListPresenter.class).to(StaffListPresenterImpl.class);
		bind(StaffListDisplay.class).to(StaffListWidget.class);
		bind(StaffListPrintPresenter.class).to(StaffListPrintPresenterImpl.class);
		bind(StaffListPrintDisplay.class).to(StaffListPrintWidget.class);
	}

}
