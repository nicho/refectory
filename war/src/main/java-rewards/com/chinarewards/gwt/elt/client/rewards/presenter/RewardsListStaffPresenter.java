package com.chinarewards.gwt.elt.client.rewards.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface RewardsListStaffPresenter extends
		Presenter<RewardsListStaffPresenter.RewardsListStaffDisplay> {

	public void initRewardsList(RewardPageType pageType);

	public static interface RewardsListStaffDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();

		public HasValue<String> getWinnerName();

		public ListBox getRewardsItem();

		public DateBox getRewardsTime();

		public Panel getResultPanel();

		public Panel getResultpage();

		public void setDataCount(String text);

		public void setBreadCrumbs(Widget breadCrumbs);

	}
}
