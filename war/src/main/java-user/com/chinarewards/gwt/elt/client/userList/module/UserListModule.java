package com.chinarewards.gwt.elt.client.userList.module;


import com.chinarewards.gwt.elt.client.userList.presenter.UserListPresenter;
import com.chinarewards.gwt.elt.client.userList.presenter.UserListPresenter.UserListDisplay;
import com.chinarewards.gwt.elt.client.userList.presenter.UserListPresenterImpl;
import com.chinarewards.gwt.elt.client.userList.view.UserListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class UserListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserListPresenter.class).to(UserListPresenterImpl.class);
		bind(UserListDisplay.class).to(UserListWidget.class);
	}

}
