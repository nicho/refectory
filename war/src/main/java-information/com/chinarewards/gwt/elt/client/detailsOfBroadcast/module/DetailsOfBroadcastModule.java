package com.chinarewards.gwt.elt.client.detailsOfBroadcast.module;


import com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter.DetailsOfBroadcastPresenter;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter.DetailsOfBroadcastPresenter.DetailsOfBroadcastDisplay;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter.DetailsOfBroadcastPresenterImpl;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.view.DetailsOfBroadcastWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DetailsOfBroadcastModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DetailsOfBroadcastPresenter.class).to(DetailsOfBroadcastPresenterImpl.class);
		bind(DetailsOfBroadcastDisplay.class).to(DetailsOfBroadcastWidget.class);
	}

}
