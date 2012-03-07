package com.chinarewards.gwt.elt.client.department.module;


import com.chinarewards.gwt.elt.client.department.presenter.DepartmentListPresenter;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentListPresenter.DepartmentListDisplay;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentListPresenterImpl;
import com.chinarewards.gwt.elt.client.department.view.DepartmentListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DepartmentListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DepartmentListPresenter.class).to(DepartmentListPresenterImpl.class);
		bind(DepartmentListDisplay.class).to(DepartmentListWidget.class);
	}

}
