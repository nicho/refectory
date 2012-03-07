package com.chinarewards.gwt.elt.client.message.module;


import com.chinarewards.gwt.elt.client.message.presenter.MessageListPresenter;
import com.chinarewards.gwt.elt.client.message.presenter.MessageListPresenter.MessageListDisplay;
import com.chinarewards.gwt.elt.client.message.presenter.MessageListPresenterImpl;
import com.chinarewards.gwt.elt.client.message.view.MessageListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class MessageListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MessageListPresenter.class).to(MessageListPresenterImpl.class);
		bind(MessageListDisplay.class).to(MessageListWidget.class);
	}

}
