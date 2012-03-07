package com.chinarewards.gwt.elt.client.rewards.module;


import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListPresenter;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListPresenter.RewardsListDisplay;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListPresenterImpl;
import com.chinarewards.gwt.elt.client.rewards.view.RewardsListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class RewardsListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RewardsListPresenter.class).to(RewardsListPresenterImpl.class);
		bind(RewardsListDisplay.class).to(RewardsListWidget.class);
	}

}
