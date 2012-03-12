package com.chinarewards.gwt.elt.client.userList.module;


import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter.StaffListDisplay;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenterImpl;
import com.chinarewards.gwt.elt.client.staffList.view.StaffListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class UserListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserListPresenter.class).to(UserListPresenterImpl.class);
		bind(StaffListDisplay.class).to(UserListWidget.class);
	}

}
