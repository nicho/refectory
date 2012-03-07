package com.chinarewards.gwt.elt.client.colleagueParticular.module;


import com.chinarewards.gwt.elt.client.colleagueParticular.presenter.ColleagueParticularPresenter;
import com.chinarewards.gwt.elt.client.colleagueParticular.presenter.ColleagueParticularPresenter.ColleagueParticularDisplay;
import com.chinarewards.gwt.elt.client.colleagueParticular.presenter.ColleagueParticularPresenterImpl;
import com.chinarewards.gwt.elt.client.colleagueParticular.view.ColleagueParticularWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class ColleagueParticularModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ColleagueParticularPresenter.class).to(ColleagueParticularPresenterImpl.class);
		bind(ColleagueParticularDisplay.class).to(ColleagueParticularWidget.class);
	}

}
