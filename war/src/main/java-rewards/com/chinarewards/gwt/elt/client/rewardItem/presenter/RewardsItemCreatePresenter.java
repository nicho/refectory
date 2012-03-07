package com.chinarewards.gwt.elt.client.rewardItem.presenter;


import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public interface RewardsItemCreatePresenter extends Presenter<RewardsItemCreatePresenter.RewardsItemDisplay> {
	//void initRewardsItemOrRewardsTemplate(Object item);

     void initInstanceId(String instanceId,RewardsItemClient item);
	
	//void checkIsAmountRoleLevel();
	public ParticipateInfoClient getNominateInfo();
	public static interface RewardsItemDisplay extends Display {

		public HasValue<String> getRewardsName();
	
		public HasValue<String> getDefinition();

		public HasValue<String> getStandard();
    
		public Integer getTmdays();//预期提名的天数
		
		public Integer getTmday();//周期性中用一的下次提名的天数

		public HasClickHandlers getFrequencySettingClick();

		public HasValue<Date> getStartTime();
        
		public Integer getTotalJF();
		
		void setBreadCrumbs(Widget breadCrumbs) ;
		
		public String getRewardsType();
		
		public HasValue<Date> getNextPublishTime();

		public HasValue<Date> getNextRewardsTime();
		
		public HasValue<Date> getExpectTime();

		public HasClickHandlers getBackClick() ;
		public HasClickHandlers getBackStoreClick() ;
		
		public Integer getRewardsFrom();
		
	//	public Integer getRewardsTo() ;
	//	public String getBuildDept();
		public HasValue<Boolean> getEnableCbx();
		
		public void setRemainCount(String text) ;
		public void setTitle(String text) ;

		public HasValue<Boolean> getAutoCbx();

		public HasClickHandlers getSaveClick();
		
		public HasClickHandlers getSaveStoreClick();

		public void clear();
       
		public FrequencyClient getFrequencyObj();

		/**
		 * 选择员工模块
		 * 
		 * @param w
		 */
		public void initStaffBlock(Widget w);

		public String getRewardsUnit();

		public HasValueChangeHandlers<Date> getStartTimeChangeHandler();

		public HasValueChangeHandlers<Date> getRewardsTimeChangeHandler();

		public HasValue<Boolean> getSpecialCbx();

		public HasValue<Boolean> getBirthRadio();

		/**
		 * 选择 设立部门和入账部门
		 * 
		 * @param deptIds
		 *            设置根列表
		 */
		/** 金额规则 **/
		
			/** 基本信息 **/
		
		// 获奖人数
		public HasValue<String> getPeopleSizeLimit();
		
		// 显示奖项
		public void showRewardsItem(RewardsItemClient rewardsItem,boolean isItemStore);

		// 显示模板
	//	public void showRewardsTemplate(RewardsTemplateClient tmeplate,boolean initDo);

		// 显示频率
		public void showFrequencyInfo(FrequencyClient frequency);

		// 显示提名人
		void showJudgeInfo(RewardsItemClient info);
		
		public void setNextRewardsTimeVisible(boolean visible);
		
		/*
		 * 获取金额监听事件源
		 */
		public TextBox getRewardsFromFocus();
		

		HasValueChangeHandlers<Boolean> onetimesClick();
		HasValueChangeHandlers<Boolean> moretimesClick();
		CheckBox getAutoCbxElement();
		
		HasClickHandlers getChooseStaffBtnClick();
		SpecialTextArea<OrganicationClient> getSpecialTextArea();
		
		
		// 得到提名人的id
		List<String> getNominateIds();
		void setRewardBackButtonDisplay(boolean status);
		void setRewardButtonDisplay(boolean status);
	}
}
