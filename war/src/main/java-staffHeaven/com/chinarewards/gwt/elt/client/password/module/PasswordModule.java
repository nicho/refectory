package com.chinarewards.gwt.elt.client.password.module;

import com.chinarewards.gwt.elt.client.password.presenter.PasswordPresenter;
import com.chinarewards.gwt.elt.client.password.presenter.PasswordPresenter.PasswordDisplay;
import com.chinarewards.gwt.elt.client.password.presenter.PasswordPresenterImpl;
import com.chinarewards.gwt.elt.client.password.view.PasswordWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class PasswordModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(PasswordPresenter.class).to(PasswordPresenterImpl.class);
		bind(PasswordDisplay.class).to(PasswordWidget.class);
	}

}
