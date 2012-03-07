package com.chinarewards.gwt.elt.client.colleague.module;


import com.chinarewards.gwt.elt.client.colleague.presenter.ColleagueListPresenter;
import com.chinarewards.gwt.elt.client.colleague.presenter.ColleagueListPresenter.ColleagueListDisplay;
import com.chinarewards.gwt.elt.client.colleague.presenter.ColleagueListPresenterImpl;
import com.chinarewards.gwt.elt.client.colleague.view.ColleagueListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class ColleagueListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ColleagueListPresenter.class).to(ColleagueListPresenterImpl.class);
		bind(ColleagueListDisplay.class).to(ColleagueListWidget.class);
	}

}
