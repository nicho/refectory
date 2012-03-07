package com.chinarewards.gwt.elt.client.broadcastReply.module;


import com.chinarewards.gwt.elt.client.broadcastReply.presenter.BroadcastReplyPresenter;
import com.chinarewards.gwt.elt.client.broadcastReply.presenter.BroadcastReplyPresenter.BroadcastReplyDisplay;
import com.chinarewards.gwt.elt.client.broadcastReply.presenter.BroadcastReplyPresenterImpl;
import com.chinarewards.gwt.elt.client.broadcastReply.view.BroadcastReplyWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class BroadcastReplyModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BroadcastReplyPresenter.class).to(BroadcastReplyPresenterImpl.class);
		bind(BroadcastReplyDisplay.class).to(BroadcastReplyWidget.class);
	}

}
