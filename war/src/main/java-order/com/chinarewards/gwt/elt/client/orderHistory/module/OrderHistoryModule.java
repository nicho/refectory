package com.chinarewards.gwt.elt.client.orderHistory.module;


import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenter;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenter.OrderHistoryDisplay;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenterImpl;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryViewPresenter;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryViewPresenter.OrderHistoryViewDisplay;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryViewPresenterImpl;
import com.chinarewards.gwt.elt.client.orderHistory.view.OrderHistoryViewWidget;
import com.chinarewards.gwt.elt.client.orderHistory.view.OrderHistoryWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class OrderHistoryModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderHistoryPresenter.class).to(OrderHistoryPresenterImpl.class);
		bind(OrderHistoryDisplay.class).to(OrderHistoryWidget.class);
		
		bind(OrderHistoryViewPresenter.class).to(OrderHistoryViewPresenterImpl.class);
		bind(OrderHistoryViewDisplay.class).to(OrderHistoryViewWidget.class);
		
	}

}
