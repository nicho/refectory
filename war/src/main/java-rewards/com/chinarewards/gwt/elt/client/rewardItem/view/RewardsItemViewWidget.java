package com.chinarewards.gwt.elt.client.rewardItem.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.frequency.FrequencyCalculator;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewPresenter.RewardsItemViewDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsBaseInfo;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.SpecialCondition;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
import com.chinarewards.gwt.elt.util.DateTool;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class RewardsItemViewWidget extends Composite implements RewardsItemViewDisplay {

	/** 基本信息 **/
	// 名称
	@UiField
	Label rewardsName;
	
	// 定义
	@UiField
	Label rewardsDefinition;
	// 标准
	@UiField
	Label standard;
	
	// 限制人数
	@UiField
	Label peopleSizeLimit;
	@UiField
	//总积分
	Label totalJF;
	@UiField
	Label rewardsFrom;
   //一次性的提前几天提名
	@UiField
	InlineLabel tmdays;
	//多次性的提前几天提名
	@UiField
	InlineLabel tmday;
	/** 频率规则 **/
	// 奖项模式
	@UiField Label itemModel;
	// 频率设定文字
	@UiField
	Label settingText;
	// 频率设定按钮
	
	// 开始时间
	@UiField
	Label startTime;
	
	// 下次公布时间
	@UiField
	Label nextPublicTime;
	
	// 下次颁奖时间
	@UiField
	InlineLabel nextRewardsTime;
	//期望颁奖时间
	@UiField
	InlineLabel expectTime;
	// 是否自动
	@UiField
	CheckBox autoCbx;
	// 特殊条件选择
	@UiField
	CheckBox specialCbx;
	// 生日奖
	@UiField
	RadioButton birthRadio;
    
	@UiField
	Panel breadCrumbs;	
	/** 存储有用的信息 **/
	FrequencyClient frequency;
	String rewardsUnit;
	
  
	//显示提名人的面板
	@UiField
	Panel staffAreaPanel;
		
	//显示候选人的面板
	@UiField
	Panel staffPanel;
	
	@UiField
	Button back;
	@UiField
	Button update;
	
	@UiField
	Button backStore;
	@UiField
	Button updateStore;
	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}
   
	@Override
	public HasClickHandlers getUpdateClick() {
		return update;
	}
	
	public HasClickHandlers getBackStoreClick() {
		return backStore;
	}
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}
	@Override
	public HasClickHandlers getUpdateStoreClick() {
		return updateStore;
	}

	interface RewardsItemViewWidgetBinder extends
			UiBinder<Widget, RewardsItemViewWidget> {

	}

	private static RewardsItemViewWidgetBinder uiBinder = GWT
			.create(RewardsItemViewWidgetBinder.class);

	
	@Inject
	public RewardsItemViewWidget( DispatchAsync dispatch,ErrorHandler errorHandler, SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
				
	}
  		
	@Override
	public Widget asWidget() {
		return this;
	}

	
	@Override
	public FrequencyClient getFrequencyObj() {
		return frequency;
	}


	@Override
	public HasValue<Boolean> getSpecialCbx() {
		return specialCbx;
	}

	@Override
	public HasValue<Boolean> getBirthRadio() {
		return birthRadio;
	}

	@Override
	public void showFrequencyInfo(FrequencyClient frequency) {
		String text = FrequencyCalculator.getTextFromFrequency(frequency);
		this.frequency = frequency;
		settingText.setText(text);
	}

	

	@Override
	public HasValue<Boolean> getAutoCbx() {
		return autoCbx;
	}
	// 显示提名人
	@Override
	public void showJudgeInfo(RewardsItemClient info){
			String judgeStr = "";
			ParticipateInfoClient participateInfo = info.getTmInfo();
			 if (participateInfo instanceof SomeoneClient) {
					for (OrganicationClient org : ((SomeoneClient) participateInfo)	.getOrganizations()) {
						 judgeStr +=org.getName()+"   ";
					}
			}
			 InlineLabel nominatelab = new InlineLabel(judgeStr);
			 staffAreaPanel.add(nominatelab);
			 
	}

	@Override
	public void showParticipateInfo(RewardsBaseInfo info) {
		String candidate="";
		ParticipateInfoClient participateInfo = info.getParticipateInfo();
		if (participateInfo instanceof SomeoneClient) {
			
			for (OrganicationClient org : ((SomeoneClient) participateInfo)	.getOrganizations()) {
				candidate +=org.getName()+"    ";
			}
		}else{
			candidate="全体员工";
		}
		 InlineLabel candidatelab = new InlineLabel(candidate);
		 staffPanel.add(candidatelab);
	}

	public void showRewardsItem(RewardsItemClient rewardsItem,boolean isItemStore) {
		if (rewardsItem.getFrequency() != null) {
			// 显示出下次颁奖时间
			nextRewardsTime.getElement().getParentElement().getParentElement()
					.removeClassName(CssStyleConstants.hidden);
				
		} 
		if(isItemStore==true){//是奖项库的修改按钮不可用
			update.setVisible(false);
			back.setVisible(false);
			
		}else{
			updateStore.setVisible(false);
			backStore.setVisible(false);
			
		}
			
		 showJudgeInfo(rewardsItem);//显示的提名人
		 showParticipateInfo(rewardsItem.getBaseInfo());//显示的候选人
	
		 rewardsName.setText(rewardsItem.getName());
		 rewardsDefinition.setText(rewardsItem.getDefinition());
		 standard.setText(rewardsItem.getStandard());
		 rewardsUnit = rewardsItem.getRewardsUnit();
		 peopleSizeLimit.setText(StringUtil.valueOf(rewardsItem.getSizeLimit()));
		  startTime.setText(DateTool.dateToString(rewardsItem.getStartTime()));
		  nextRewardsTime.setText(DateTool.dateToString(rewardsItem.getNextTime()));
		   
	      rewardsFrom.setText(StringUtil.valueOf(rewardsItem.getRewardsFrom()));
	      tmday.setText(StringUtil.valueOf(rewardsItem.getTmdays()));
		  tmdays.setText(StringUtil.valueOf(rewardsItem.getTmdays()));
		  totalJF.setText(StringUtil.valueOf(rewardsItem.getTotalJF()));
		  expectTime.setText(DateTool.dateToString(rewardsItem.getNextTime()));
		  nextPublicTime.setText(DateTool.dateToString(rewardsItem.getNextPublishTime()));
		  showFrequencyInfo(rewardsItem.getFrequency());
		  autoCbx.setValue(rewardsItem.isAuto(), true);
	 	  specialCbx.setValue(rewardsItem.isHasSpecialCondition(), true);
	 	 if(rewardsItem.isAuto()==false)//是自动奖隐藏提名
	 		 staffPanel.getElement().getParentElement().getParentElement().removeClassName(CssStyleConstants.hidden);
		  else
			 staffPanel.getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);  
		if (SpecialCondition.birth == rewardsItem.getCondition()) {
			birthRadio.setValue(true);
			birthRadio.getElement().removeClassName(CssStyleConstants.hidden);
		} else {
			birthRadio.setValue(false);
			birthRadio.getElement().addClassName(CssStyleConstants.hidden);
		}
       if(rewardsItem.isPeriodEnable()==true){
    	    itemModel.setText("周期性"); 
    	    settingText.getElement().getParentElement().getParentElement().getParentElement().removeClassName(CssStyleConstants.hidden);
			expectTime.getElement().getParentElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
       }else{
    	   itemModel.setText("一次性");
    	   settingText.getElement().getParentElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
		   expectTime.getElement().getParentElement().getParentElement().getParentElement().removeClassName(CssStyleConstants.hidden);
       }
		
	}


		
	
}
