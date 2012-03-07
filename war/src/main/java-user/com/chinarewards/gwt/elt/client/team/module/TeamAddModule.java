package com.chinarewards.gwt.elt.client.team.module;


import com.chinarewards.gwt.elt.client.team.presenter.TeamAddPresenter;
import com.chinarewards.gwt.elt.client.team.presenter.TeamAddPresenter.TeamAddDisplay;
import com.chinarewards.gwt.elt.client.team.presenter.TeamAddPresenterImpl;
import com.chinarewards.gwt.elt.client.team.view.TeamAddWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class TeamAddModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TeamAddPresenter.class).to(TeamAddPresenterImpl.class);
		bind(TeamAddDisplay.class).to(TeamAddWidget.class);
	}

}
