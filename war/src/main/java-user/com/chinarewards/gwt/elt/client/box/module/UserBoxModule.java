package com.chinarewards.gwt.elt.client.box.module;


import com.chinarewards.gwt.elt.client.box.presenter.UserBoxPresenter;
import com.chinarewards.gwt.elt.client.box.presenter.UserBoxPresenter.UserBoxDisplay;
import com.chinarewards.gwt.elt.client.box.presenter.UserBoxPresenterImpl;
import com.chinarewards.gwt.elt.client.box.view.UserBoxWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class UserBoxModule extends AbstractGinModule {

	@Override
	protected void configure() {
			
		bind(UserBoxPresenter.class).to(UserBoxPresenterImpl.class);
		bind(UserBoxDisplay.class).to(UserBoxWidget.class);
	}

}
