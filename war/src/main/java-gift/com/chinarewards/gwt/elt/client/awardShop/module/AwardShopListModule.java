package com.chinarewards.gwt.elt.client.awardShop.module;


import com.chinarewards.gwt.elt.client.awardShop.presenter.AwardShopListPresenter;
import com.chinarewards.gwt.elt.client.awardShop.presenter.AwardShopListPresenter.AwardShopListDisplay;
import com.chinarewards.gwt.elt.client.awardShop.presenter.AwardShopListPresenterImpl;
import com.chinarewards.gwt.elt.client.awardShop.view.AwardShopListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class AwardShopListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AwardShopListPresenter.class).to(AwardShopListPresenterImpl.class);
		bind(AwardShopListDisplay.class).to(AwardShopListWidget.class);
	}

}
