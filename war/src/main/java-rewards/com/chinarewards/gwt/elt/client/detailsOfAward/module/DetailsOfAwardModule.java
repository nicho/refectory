package com.chinarewards.gwt.elt.client.detailsOfAward.module;


import com.chinarewards.gwt.elt.client.detailsOfAward.presenter.DetailsOfAwardPresenter;
import com.chinarewards.gwt.elt.client.detailsOfAward.presenter.DetailsOfAwardPresenter.DetailsOfAwardDisplay;
import com.chinarewards.gwt.elt.client.detailsOfAward.presenter.DetailsOfAwardPresenterImpl;
import com.chinarewards.gwt.elt.client.detailsOfAward.view.DetailsOfAwardWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DetailsOfAwardModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DetailsOfAwardPresenter.class).to(DetailsOfAwardPresenterImpl.class);
		bind(DetailsOfAwardDisplay.class).to(DetailsOfAwardWidget.class);
	}

}
