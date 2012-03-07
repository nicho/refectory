package com.chinarewards.gwt.elt.client.nominate.module;


import com.chinarewards.gwt.elt.client.nominate.presenter.NominatePresenter;
import com.chinarewards.gwt.elt.client.nominate.presenter.NominatePresenter.NominateDisplay;
import com.chinarewards.gwt.elt.client.nominate.presenter.NominatePresenterImpl;
import com.chinarewards.gwt.elt.client.nominate.view.NominateWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class NominateModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(NominatePresenter.class).to(NominatePresenterImpl.class);
		bind(NominateDisplay.class).to(NominateWidget.class);
	}

}
