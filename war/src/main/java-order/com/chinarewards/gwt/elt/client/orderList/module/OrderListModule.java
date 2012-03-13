package com.chinarewards.gwt.elt.client.orderList.module;


import com.chinarewards.gwt.elt.client.orderList.presenter.OrderListPresenter;
import com.chinarewards.gwt.elt.client.orderList.presenter.OrderListPresenter.OrderListDisplay;
import com.chinarewards.gwt.elt.client.orderList.presenter.OrderListPresenterImpl;
import com.chinarewards.gwt.elt.client.orderList.view.OrderListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class OrderListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderListPresenter.class).to(OrderListPresenterImpl.class);
		bind(OrderListDisplay.class).to(OrderListWidget.class);
	}

}
