package com.chinarewards.gwt.elt.client.dishesTypeList.module;


import com.chinarewards.gwt.elt.client.dishesTypeList.presenter.DishesTypeListPresenter;
import com.chinarewards.gwt.elt.client.dishesTypeList.presenter.DishesTypeListPresenter.DishesTypeListDisplay;
import com.chinarewards.gwt.elt.client.dishesTypeList.presenter.DishesTypeListPresenterImpl;
import com.chinarewards.gwt.elt.client.dishesTypeList.view.DishesTypeListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DishesTypeListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesTypeListPresenter.class).to(DishesTypeListPresenterImpl.class);
		bind(DishesTypeListDisplay.class).to(DishesTypeListWidget.class);
	}

}
