package com.chinarewards.gwt.elt.client.department.module;


import com.chinarewards.gwt.elt.client.department.presenter.DepartmentLeaderPresenter;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentLeaderPresenter.DepartmentLeaderDisplay;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentLeaderPresenterImpl;
import com.chinarewards.gwt.elt.client.department.view.DepartmentLeaderWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DepartmentLeaderModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DepartmentLeaderPresenter.class).to(DepartmentLeaderPresenterImpl.class);
		bind(DepartmentLeaderDisplay.class).to(DepartmentLeaderWidget.class);
	}

}
