package com.chinarewards.gwt.elt.client.register.presenter;

import com.chinarewards.gwt.elt.client.register.presenter.RegisterPresenter.RegisterDisplay;
import com.chinarewards.gwt.elt.client.register.view.RegisterWidget;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class RegisterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RegisterPresenter.class).to(RegisterPresenterImpl.class).in(Singleton.class);
		bind(RegisterDisplay.class).to(RegisterWidget.class);
	}

}
