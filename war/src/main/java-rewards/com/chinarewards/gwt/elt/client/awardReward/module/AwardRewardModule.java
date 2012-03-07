package com.chinarewards.gwt.elt.client.awardReward.module;


import com.chinarewards.gwt.elt.client.awardReward.presenter.AwardRewardPresenter;
import com.chinarewards.gwt.elt.client.awardReward.presenter.AwardRewardPresenter.AwardRewardDisplay;
import com.chinarewards.gwt.elt.client.awardReward.presenter.AwardRewardPresenterImpl;
import com.chinarewards.gwt.elt.client.awardReward.view.AwardRewardWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class AwardRewardModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AwardRewardPresenter.class).to(AwardRewardPresenterImpl.class);
		bind(AwardRewardDisplay.class).to(AwardRewardWidget.class);
	}

}
