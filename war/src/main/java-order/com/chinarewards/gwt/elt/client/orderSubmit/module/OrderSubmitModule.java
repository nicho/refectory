package com.chinarewards.gwt.elt.client.orderSubmit.module;


import com.chinarewards.gwt.elt.client.orderSubmit.presenter.OrderSubmitPresenter;
import com.chinarewards.gwt.elt.client.orderSubmit.presenter.OrderSubmitPresenter.OrderSubmitDisplay;
import com.chinarewards.gwt.elt.client.orderSubmit.presenter.OrderSubmitPresenterImpl;
import com.chinarewards.gwt.elt.client.orderSubmit.view.OrderSubmitWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class OrderSubmitModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderSubmitPresenter.class).to(OrderSubmitPresenterImpl.class);
		bind(OrderSubmitDisplay.class).to(OrderSubmitWidget.class);
	}

}
