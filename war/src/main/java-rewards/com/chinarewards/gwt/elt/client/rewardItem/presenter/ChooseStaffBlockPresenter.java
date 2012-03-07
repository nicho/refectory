package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsBaseInfo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;

public interface ChooseStaffBlockPresenter extends	Presenter<ChooseStaffBlockPresenter.ChooseStaffBlockDisplay> {

	/* 定义对外的接口 */
	//
	// /**
	// * 得到限额的人数<br/>
	// *
	// * 1. null -- 代表不限额<br/>
	// * 2. 正数或0 -- 代表限额的数量<br/>
	// * 3. -1 -- 数字未填或parse数字出错
	// */
	// public Integer getLimitNum();

	/**
	 * 得到候选人信息
	 * 
	 * @return
	 */
	public ParticipateInfoClient getparticipateInfo();

	public static interface ChooseStaffBlockDisplay extends Display {

		// 选中所有员工
		HasValue<Boolean> isEveryone();

		// 部分员工
		HasValue<Boolean> isSomeone();

		// 得到候选人的id
		List<String> getRealOrginzationIds();

		// 显示候选人
		void showParticipateInfo(RewardsBaseInfo info);

		// Map<String, OrganicationClient> getStaffMap();

		SpecialTextArea<OrganicationClient> getSpecialTextArea();

		HasClickHandlers getChooseStaffBtnClick();

		public Panel getSuggestBoxPanel();
		
		
	}
}
