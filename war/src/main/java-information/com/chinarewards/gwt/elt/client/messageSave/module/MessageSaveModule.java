package com.chinarewards.gwt.elt.client.messageSave.module;


import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenter;
import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenter.MessageSaveDisplay;
import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenterImpl;
import com.chinarewards.gwt.elt.client.messageSave.view.MessageSaveWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class MessageSaveModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MessageSavePresenter.class).to(MessageSavePresenterImpl.class);
		bind(MessageSaveDisplay.class).to(MessageSaveWidget.class);
	}

}
