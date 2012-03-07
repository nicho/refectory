package com.chinarewards.gwt.elt.client.orderConfirmation.module;


import com.chinarewards.gwt.elt.client.order.presenter.OrderListPresenter;
import com.chinarewards.gwt.elt.client.order.presenter.OrderListPresenterImpl;
import com.chinarewards.gwt.elt.client.order.presenter.OrderListPresenter.OrderListDisplay;
import com.chinarewards.gwt.elt.client.order.view.OrderListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class OrderListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderListPresenter.class).to(OrderListPresenterImpl.class);
		bind(OrderListDisplay.class).to(OrderListWidget.class);
	}

}
