package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsBaseInfo;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface RewardsItemViewStaffPresenter extends
		Presenter<RewardsItemViewStaffPresenter.RewardsItemViewStaffDisplay> {
	
	void initWidget(RewardsItemClient item);

	public static interface RewardsItemViewStaffDisplay extends Display {

		void setBreadCrumbs(Widget breadCrumbs);

		public FrequencyClient getFrequencyObj();

		public HasClickHandlers getBackClick();

		public HasClickHandlers getUpdateClick();

		public void showRewardsItem(RewardsItemClient rewardsItem);

		public void showFrequencyInfo(FrequencyClient frequency);// 显示频率

		public void showJudgeInfo(RewardsItemClient info); // 显示提名人

		public void showParticipateInfo(RewardsBaseInfo info); // 显示候选人
	}
}
