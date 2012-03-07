package com.chinarewards.gwt.elt.client.gift.module;


import com.chinarewards.gwt.elt.client.gift.presenter.GiftListPresenter;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftListPresenter.GiftListDisplay;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftListPresenterImpl;
import com.chinarewards.gwt.elt.client.gift.view.GiftListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class GiftListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GiftListPresenter.class).to(GiftListPresenterImpl.class);
		bind(GiftListDisplay.class).to(GiftListWidget.class);
	}

}
