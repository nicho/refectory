package com.chinarewards.gwt.elt.client.corpBroadcast.module;


import com.chinarewards.gwt.elt.client.corpBroadcast.presenter.CorpBroadcastPresenter;
import com.chinarewards.gwt.elt.client.corpBroadcast.presenter.CorpBroadcastPresenter.CorpBroadcastDisplay;
import com.chinarewards.gwt.elt.client.corpBroadcast.presenter.CorpBroadcastPresenterImpl;
import com.chinarewards.gwt.elt.client.corpBroadcast.view.CorpBroadcastWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class CorpBroadcastModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CorpBroadcastPresenter.class).to(CorpBroadcastPresenterImpl.class);
		bind(CorpBroadcastDisplay.class).to(CorpBroadcastWidget.class);
	}

}
