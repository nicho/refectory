package com.chinarewards.gwt.elt.client.team.module;


import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter.OrderViewDisplay;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenterImpl;
import com.chinarewards.gwt.elt.client.order.view.OrderViewWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class TeamViewModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderViewPresenter.class).to(OrderViewPresenterImpl.class);
		bind(OrderViewDisplay.class).to(OrderViewWidget.class);
		
		
	}

}
