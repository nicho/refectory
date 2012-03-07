package com.chinarewards.gwt.elt.client.staff.module;


import com.chinarewards.gwt.elt.client.staff.presenter.LeadTimePresenter;
import com.chinarewards.gwt.elt.client.staff.presenter.LeadTimePresenter.LeadTimeDisplay;
import com.chinarewards.gwt.elt.client.staff.presenter.LeadTimePresenterImpl;
import com.chinarewards.gwt.elt.client.staff.view.LeadTimeWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class LeadTimeModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LeadTimePresenter.class).to(LeadTimePresenterImpl.class);
		bind(LeadTimeDisplay.class).to(LeadTimeWidget.class);
	}

}
