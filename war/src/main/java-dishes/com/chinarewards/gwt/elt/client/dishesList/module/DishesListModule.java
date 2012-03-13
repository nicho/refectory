package com.chinarewards.gwt.elt.client.dishesList.module;


import com.chinarewards.gwt.elt.client.dishesList.presenter.DishesListPresenter;
import com.chinarewards.gwt.elt.client.dishesList.presenter.DishesListPresenter.DishesListDisplay;
import com.chinarewards.gwt.elt.client.dishesList.presenter.DishesListPresenterImpl;
import com.chinarewards.gwt.elt.client.dishesList.view.DishesListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DishesListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DishesListPresenter.class).to(DishesListPresenterImpl.class);
		bind(DishesListDisplay.class).to(DishesListWidget.class);
	}

}
