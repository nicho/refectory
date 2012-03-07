package com.chinarewards.gwt.elt.client.chooseStaff.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChoosePanelParam;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ChooseStaffPanelPresenter extends	Presenter<ChooseStaffPanelPresenter.ChooseStaffPanelDisplay> {

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

	public void setRewardId(String rewardId);
	public void initChoosePanel(InitChoosePanelParam initChooseParam);
	public static interface ChooseStaffPanelDisplay extends Display {

		
		// 得到候选人的id,和名称
		List<String[]> getRealOrginzationIds();


		SpecialTextArea<OrganicationClient> getSpecialTextArea();

		HasClickHandlers getChooseStaffBtnClick();

		void setTopName(String topName);
		void setChooseBtnName(String name);
		void cleanStaffTextAreaPanel();
	}
}
