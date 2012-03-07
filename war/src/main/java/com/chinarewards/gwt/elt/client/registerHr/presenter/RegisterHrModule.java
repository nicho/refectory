package com.chinarewards.gwt.elt.client.registerHr.presenter;

import com.chinarewards.gwt.elt.client.registerHr.presenter.RegisterHrPresenter.RegisterHrDisplay;
import com.chinarewards.gwt.elt.client.registerHr.view.RegisterHrWidget;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class RegisterHrModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RegisterHrPresenter.class).to(RegisterHrPresenterImpl.class).in(Singleton.class);
		bind(RegisterHrDisplay.class).to(RegisterHrWidget.class);
	}

}
