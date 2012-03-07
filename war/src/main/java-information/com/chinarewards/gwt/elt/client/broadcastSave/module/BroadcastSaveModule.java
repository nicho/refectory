package com.chinarewards.gwt.elt.client.broadcastSave.module;


import com.chinarewards.gwt.elt.client.broadcastSave.presenter.BroadcastSavePresenter;
import com.chinarewards.gwt.elt.client.broadcastSave.presenter.BroadcastSavePresenter.BroadcastSaveDisplay;
import com.chinarewards.gwt.elt.client.broadcastSave.presenter.BroadcastSavePresenterImpl;
import com.chinarewards.gwt.elt.client.broadcastSave.view.BroadcastSaveWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class BroadcastSaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BroadcastSavePresenter.class).to(BroadcastSavePresenterImpl.class);
		bind(BroadcastSaveDisplay.class).to(BroadcastSaveWidget.class);
	}

}
