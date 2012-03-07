package com.chinarewards.gwt.elt.client.gift.module;

import com.chinarewards.gwt.elt.client.gift.presenter.GiftPresenter;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftPresenter.GiftDisplay;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftPresenterImpl;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftViewPresenter.GiftViewDisplay;
import com.chinarewards.gwt.elt.client.gift.view.GiftViewWidget;
import com.chinarewards.gwt.elt.client.gift.view.GiftWidget;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftViewPresenter;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftViewPresenterImpl;
import com.google.gwt.inject.client.AbstractGinModule;

public class GiftModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GiftPresenter.class).to(GiftPresenterImpl.class);
		bind(GiftDisplay.class).to(GiftWidget.class);

		bind(GiftViewPresenter.class).to(GiftViewPresenterImpl.class);
		bind(GiftViewDisplay.class).to(GiftViewWidget.class);
	}
}
