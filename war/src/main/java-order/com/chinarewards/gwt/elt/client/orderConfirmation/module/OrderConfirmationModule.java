package com.chinarewards.gwt.elt.client.orderConfirmation.module;


import com.chinarewards.gwt.elt.client.orderConfirmation.presenter.OrderConfirmationPresenter;
import com.chinarewards.gwt.elt.client.orderConfirmation.presenter.OrderConfirmationPresenter.OrderConfirmationDisplay;
import com.chinarewards.gwt.elt.client.orderConfirmation.presenter.OrderConfirmationPresenterImpl;
import com.chinarewards.gwt.elt.client.orderConfirmation.view.OrderConfirmationWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class OrderConfirmationModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderConfirmationPresenter.class).to(OrderConfirmationPresenterImpl.class);
		bind(OrderConfirmationDisplay.class).to(OrderConfirmationWidget.class);
	}

}
