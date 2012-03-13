package com.chinarewards.gwt.elt.client.restaurantList.module;


import com.chinarewards.gwt.elt.client.restaurantList.presenter.RestaurantListPresenter;
import com.chinarewards.gwt.elt.client.restaurantList.presenter.RestaurantListPresenter.RestaurantListDisplay;
import com.chinarewards.gwt.elt.client.restaurantList.presenter.RestaurantListPresenterImpl;
import com.chinarewards.gwt.elt.client.restaurantList.view.RestaurantListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class RestaurantListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RestaurantListPresenter.class).to(RestaurantListPresenterImpl.class);
		bind(RestaurantListDisplay.class).to(RestaurantListWidget.class);
	}

}
