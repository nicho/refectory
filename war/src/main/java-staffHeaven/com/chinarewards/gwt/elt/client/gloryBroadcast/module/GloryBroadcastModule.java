package com.chinarewards.gwt.elt.client.gloryBroadcast.module;


import com.chinarewards.gwt.elt.client.gloryBroadcast.presenter.GloryBroadcastPresenter;
import com.chinarewards.gwt.elt.client.gloryBroadcast.presenter.GloryBroadcastPresenter.GloryBroadcastDisplay;
import com.chinarewards.gwt.elt.client.gloryBroadcast.presenter.GloryBroadcastPresenterImpl;
import com.chinarewards.gwt.elt.client.gloryBroadcast.view.GloryBroadcastWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class GloryBroadcastModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GloryBroadcastPresenter.class).to(GloryBroadcastPresenterImpl.class);
		bind(GloryBroadcastDisplay.class).to(GloryBroadcastWidget.class);
	}

}
