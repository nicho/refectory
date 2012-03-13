package com.chinarewards.gwt.elt.client;


import com.chinarewards.gwt.elt.client.breadCrumbs.module.BreadCrumbsModule;
import com.chinarewards.gwt.elt.client.core.presenter.DockModule;
import com.chinarewards.gwt.elt.client.dictionaryList.module.DictionaryListModule;
import com.chinarewards.gwt.elt.client.dishesList.module.DishesListModule;
import com.chinarewards.gwt.elt.client.dishesTypeList.module.DishesTypeListModule;
import com.chinarewards.gwt.elt.client.orderList.module.OrderListModule;
import com.chinarewards.gwt.elt.client.restaurantList.module.RestaurantListModule;
import com.chinarewards.gwt.elt.client.userList.module.UserListModule;
import com.chinarewards.gwt.elt.client.win.WinModule;
import com.google.gwt.inject.client.AbstractGinModule;

public class PresenterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new DockModule());

		install(new WinModule());
		install(new BreadCrumbsModule());
		install(new UserListModule());
		install(new RestaurantListModule());
		install(new DictionaryListModule());
		install(new DishesListModule());
		install(new DishesTypeListModule());
		install(new OrderListModule());


	}

}
