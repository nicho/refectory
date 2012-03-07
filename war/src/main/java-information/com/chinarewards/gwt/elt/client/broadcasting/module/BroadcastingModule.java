package com.chinarewards.gwt.elt.client.broadcasting.module;


import com.chinarewards.gwt.elt.client.broadcasting.presenter.BroadcastingListPresenter;
import com.chinarewards.gwt.elt.client.broadcasting.presenter.BroadcastingListPresenter.BroadcastingListDisplay;
import com.chinarewards.gwt.elt.client.broadcasting.presenter.BroadcastingListPresenterImpl;
import com.chinarewards.gwt.elt.client.broadcasting.view.BroadcastingListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class BroadcastingModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BroadcastingListPresenter.class).to(BroadcastingListPresenterImpl.class);
		bind(BroadcastingListDisplay.class).to(BroadcastingListWidget.class);
	}

}
