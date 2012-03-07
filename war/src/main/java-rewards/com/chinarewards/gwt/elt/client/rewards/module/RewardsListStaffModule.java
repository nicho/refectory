package com.chinarewards.gwt.elt.client.rewards.module;

import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListStaffPresenter;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListStaffPresenter.RewardsListStaffDisplay;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListStaffPresenterImpl;
import com.chinarewards.gwt.elt.client.rewards.view.RewardsListStaffWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class RewardsListStaffModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RewardsListStaffPresenter.class).to(
				RewardsListStaffPresenterImpl.class);
		bind(RewardsListStaffDisplay.class).to(RewardsListStaffWidget.class);
	}

}
