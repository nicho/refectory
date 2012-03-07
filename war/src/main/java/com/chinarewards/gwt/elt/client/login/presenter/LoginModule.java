package com.chinarewards.gwt.elt.client.login.presenter;

import com.chinarewards.gwt.elt.client.login.presenter.LoginPresenter.LoginDisplay;
import com.google.gwt.inject.client.AbstractGinModule;

public class LoginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LoginPresenter.class).to(LoginPresenterImpl.class);
		bind(LoginDisplay.class).to(LoginWidget.class);
	}

}
