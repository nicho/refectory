package com.chinarewards.gwt.elt.client.team.module;


import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenter;
import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenter.TeamListDisplay;
import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenterImpl;
import com.chinarewards.gwt.elt.client.team.view.TeamListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class TeamListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TeamListPresenter.class).to(TeamListPresenterImpl.class);
		bind(TeamListDisplay.class).to(TeamListWidget.class);
	}

}
