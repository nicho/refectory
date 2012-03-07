package com.chinarewards.gwt.elt.client.department.module;

import com.chinarewards.gwt.elt.client.department.presenter.ChooseLeaderWinPresenter;
import com.chinarewards.gwt.elt.client.department.presenter.ChooseLeaderWinPresenter.ChooseLeaderWinDisplay;
import com.chinarewards.gwt.elt.client.department.presenter.ChooseLeaderWinPresenterImpl;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentPresenter;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentPresenter.DepartmentDisplay;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentPresenterImpl;
import com.chinarewards.gwt.elt.client.department.presenter.MergeDepartmentPresenter;
import com.chinarewards.gwt.elt.client.department.presenter.MergeDepartmentPresenter.MergeDepartmentDisplay;
import com.chinarewards.gwt.elt.client.department.presenter.MergeDepartmentPresenterImpl;
import com.chinarewards.gwt.elt.client.department.view.ChooseLeaderWinWidget;
import com.chinarewards.gwt.elt.client.department.view.DepartmentWidget;
import com.chinarewards.gwt.elt.client.department.view.MergeDepartmentWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DepartmentModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DepartmentPresenter.class).to(DepartmentPresenterImpl.class);
		bind(DepartmentDisplay.class).to(DepartmentWidget.class);

		bind(ChooseLeaderWinPresenter.class).to(
				ChooseLeaderWinPresenterImpl.class);
		bind(ChooseLeaderWinDisplay.class).to(ChooseLeaderWinWidget.class);

		bind(MergeDepartmentPresenter.class).to(
				MergeDepartmentPresenterImpl.class);
		bind(MergeDepartmentDisplay.class).to(MergeDepartmentWidget.class);

	}
}
