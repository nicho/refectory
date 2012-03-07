package com.chinarewards.gwt.elt.client.order.module;


import com.chinarewards.gwt.elt.client.order.presenter.OrderBoxPresenter;
import com.chinarewards.gwt.elt.client.order.presenter.OrderBoxPresenter.OrderBoxDisplay;
import com.chinarewards.gwt.elt.client.order.presenter.OrderBoxPresenterImpl;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter.OrderViewDisplay;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenterImpl;
import com.chinarewards.gwt.elt.client.order.view.OrderBoxWidget;
import com.chinarewards.gwt.elt.client.order.view.OrderViewWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class OrderBoxModule extends AbstractGinModule {

	@Override
	protected void configure() {
			
		bind(OrderBoxPresenter.class).to(OrderBoxPresenterImpl.class);
		bind(OrderBoxDisplay.class).to(OrderBoxWidget.class);
	}

}
