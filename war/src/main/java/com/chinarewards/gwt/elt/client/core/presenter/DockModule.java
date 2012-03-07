package com.chinarewards.gwt.elt.client.core.presenter;

import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.elt.client.core.presenter.GiftPresenter.GiftDisplay;
import com.chinarewards.gwt.elt.client.core.presenter.StaffPresenter.StaffDisplay;
import com.chinarewards.gwt.elt.client.core.view.DockWidget;
import com.chinarewards.gwt.elt.client.core.view.GiftWidget;
import com.chinarewards.gwt.elt.client.core.view.StaffWidget;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DockModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DockPresenter.class).to(DockPresenterImpl.class).in(Singleton.class);
		bind(DockDisplay.class).to(DockWidget.class);
		
		bind(StaffPresenter.class).to(StaffPresenterImpl.class);
		bind(StaffDisplay.class).to(StaffWidget.class);
		
		bind(GiftPresenter.class).to(GiftPresenterImpl.class);
		bind(GiftDisplay.class).to(GiftWidget.class);
	}

}
