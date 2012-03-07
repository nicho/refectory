package com.chinarewards.gwt.elt.client.hrbox.module;


import com.chinarewards.gwt.elt.client.hrbox.presenter.HrBoxPresenter;
import com.chinarewards.gwt.elt.client.hrbox.presenter.HrBoxPresenter.HrBoxDisplay;
import com.chinarewards.gwt.elt.client.hrbox.presenter.HrBoxPresenterImpl;
import com.chinarewards.gwt.elt.client.hrbox.view.HrBoxWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class HrBoxModule extends AbstractGinModule {

	@Override
	protected void configure() {
			
		bind(HrBoxPresenter.class).to(HrBoxPresenterImpl.class);
		bind(HrBoxDisplay.class).to(HrBoxWidget.class);
	}

}
