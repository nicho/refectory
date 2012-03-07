package com.chinarewards.gwt.elt.client;


import com.chinarewards.gwt.elt.client.breadCrumbs.module.BreadCrumbsModule;
import com.chinarewards.gwt.elt.client.core.presenter.DockModule;
import com.chinarewards.gwt.elt.client.win.WinModule;
import com.google.gwt.inject.client.AbstractGinModule;

public class PresenterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new DockModule());

		install(new WinModule());
		install(new BreadCrumbsModule());

	}

}
