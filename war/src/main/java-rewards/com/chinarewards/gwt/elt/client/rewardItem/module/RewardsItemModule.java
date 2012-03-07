package com.chinarewards.gwt.elt.client.rewardItem.module;


import com.chinarewards.gwt.elt.client.frequency.RewardStartDateCalculator;
import com.chinarewards.gwt.elt.client.frequency.SimpleRewardStartDateCalculator;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffBlockPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffBlockPresenter.ChooseStaffBlockDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffBlockPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffWinPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffWinPresenter.ChooseStaffWinDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffWinPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.FrequencySettingPresentImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.FrequencySettingPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.FrequencySettingPresenter.FrequencySettingDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemCreatePresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemCreatePresenter.RewardsItemDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemCreatePresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListCompanyPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListCompanyPresenter.RewardsItemListCompanyOtherDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListCompanyPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListPresenter.RewardsItemListDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListStaffPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListStaffPresenter.RewardsItemListStaffDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListStaffPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemStoreListPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemStoreListPresenter.RewardsItemStoreListDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemStoreListPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewPresenter.RewardsItemViewDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewStaffPresenter;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewStaffPresenter.RewardsItemViewStaffDisplay;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewStaffPresenterImpl;
import com.chinarewards.gwt.elt.client.rewardItem.view.ChooseStaffBlockWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.ChooseStaffWinWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.FrequencySettingWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemListCompanyOtherWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemListStaffWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemListWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemStoreListWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemViewStaffWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemViewWidget;
import com.chinarewards.gwt.elt.client.rewardItem.view.RewardsItemWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class RewardsItemModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RewardsItemCreatePresenter.class).to(RewardsItemCreatePresenterImpl.class);
		bind(RewardsItemDisplay.class).to(RewardsItemWidget.class);
		
		bind(ChooseStaffBlockPresenter.class).to(ChooseStaffBlockPresenterImpl.class);
		bind(ChooseStaffBlockDisplay.class).to(ChooseStaffBlockWidget.class);
		
		bind(ChooseStaffWinPresenter.class).to(ChooseStaffWinPresenterImpl.class);
		bind(ChooseStaffWinDisplay.class).to(ChooseStaffWinWidget.class);
		
		bind(FrequencySettingPresenter.class).to(FrequencySettingPresentImpl.class);
		bind(FrequencySettingDisplay.class).to(FrequencySettingWidget.class);
		
		bind(RewardStartDateCalculator.class).to(SimpleRewardStartDateCalculator.class);
		//列表
		bind(RewardsItemListPresenter.class).to(RewardsItemListPresenterImpl.class);
		bind(RewardsItemListDisplay.class).to(RewardsItemListWidget.class);
		
		//员工 
		bind(RewardsItemListStaffPresenter.class).to(RewardsItemListStaffPresenterImpl.class);
		bind(RewardsItemListStaffDisplay.class).to(RewardsItemListStaffWidget.class);
		
		bind(RewardsItemViewStaffPresenter.class).to(RewardsItemViewStaffPresenterImpl.class);
		bind(RewardsItemViewStaffDisplay.class).to(RewardsItemViewStaffWidget.class);
		
		bind(RewardsItemListCompanyPresenter.class).to(RewardsItemListCompanyPresenterImpl.class);
		bind(RewardsItemListCompanyOtherDisplay.class).to(RewardsItemListCompanyOtherWidget.class);
		
		
		bind(RewardsItemViewPresenter.class).to(RewardsItemViewPresenterImpl.class);
		bind(RewardsItemViewDisplay.class).to(RewardsItemViewWidget.class);
		
		//列表
		bind(RewardsItemStoreListPresenter.class).to(RewardsItemStoreListPresenterImpl.class);
		bind(RewardsItemStoreListDisplay.class).to(RewardsItemStoreListWidget.class);
	}
	

}
