package com.chinarewards.gwt.elt.client.shopWindow.module;


import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenter;
import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenter.ShopWindowDisplay;
import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenterImpl;
import com.chinarewards.gwt.elt.client.shopWindow.view.ShopWindowWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class ShopWindowModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ShopWindowPresenter.class).to(ShopWindowPresenterImpl.class);
		bind(ShopWindowDisplay.class).to(ShopWindowWidget.class);
	}

}
