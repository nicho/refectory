package com.chinarewards.gwt.elt.client.rewardItem.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.frequency.FrequencyCalculator;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemViewStaffPresenter.RewardsItemViewStaffDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsBaseInfo;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.util.DateTool;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class RewardsItemViewStaffWidget extends Composite implements
		RewardsItemViewStaffDisplay {

	/** 基本信息 **/
	// 名称
	@UiField
	InlineLabel rewardsName;	
	@UiField
	InlineLabel rewardsDefinition;// 定义
	@UiField
	InlineLabel standard;	// 标准

	
	@UiField
	InlineLabel peopleSizeLimit;// 限制人数
	@UiField
	InlineLabel totalJF;	// 总积分

	/** 频率规则 **/
	@UiField
	InlineLabel itemModel;	// 奖项模式
	@UiField
	InlineLabel settingText;	// 频率设定文字
	@UiField
	InlineLabel startTime;	// 开始时间
	
	/** 存储有用的信息 **/
	FrequencyClient frequency;
	String rewardsUnit;

	@UiField
	Panel staffAreaPanel;	// 显示提名人的面板
	@UiField
	Panel staffPanel;	// 显示候选人的面板

	@UiField
	Button back;
	@UiField
	Button update;
	
//	@UiField
//	Panel breadCrumbs;

	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	@Override
	public HasClickHandlers getUpdateClick() {
		return update;
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
//		this.breadCrumbs.clear();
//		this.breadCrumbs.add(breadCrumbs);
	}

	interface RewardsItemViewStaffWidgetBinder extends
			UiBinder<Widget, RewardsItemViewStaffWidget> {

	}

	private static RewardsItemViewStaffWidgetBinder uiBinder = GWT
			.create(RewardsItemViewStaffWidgetBinder.class);

	@Inject
	public RewardsItemViewStaffWidget(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager) {
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
	public void showFrequencyInfo(FrequencyClient frequency) {
		String text = FrequencyCalculator.getTextFromFrequency(frequency);
		this.frequency = frequency;
		settingText.setText(text);
	}
	
	@Override
	public void showJudgeInfo(RewardsItemClient info) {// 显示提名人
		String judgeStr = "";
		ParticipateInfoClient participateInfo = info.getTmInfo();
		if (participateInfo instanceof SomeoneClient) {
			for (OrganicationClient org : ((SomeoneClient) participateInfo)
					.getOrganizations()) {
				judgeStr += org.getName() + "   ";
			}
		}
		InlineLabel nominatelab = new InlineLabel(judgeStr);
		staffAreaPanel.add(nominatelab);
	}

	@Override
	public void showParticipateInfo(RewardsBaseInfo info) {
		String candidate = "";
		ParticipateInfoClient participateInfo = info.getParticipateInfo();
		if (participateInfo instanceof SomeoneClient) {

			for (OrganicationClient org : ((SomeoneClient) participateInfo)
					.getOrganizations()) {
				candidate += org.getName() + "    ";
			}
		} else {
			candidate = "全体员工";
		}
		InlineLabel candidatelab = new InlineLabel(candidate);
		staffPanel.add(candidatelab);
	}

	public void showRewardsItem(RewardsItemClient rewardsItem) {
		showJudgeInfo(rewardsItem);// 显示的提名人
		showParticipateInfo(rewardsItem.getBaseInfo());// 显示的候选人

		rewardsName.setText(rewardsItem.getName());
		rewardsDefinition.setText(rewardsItem.getDefinition());
		standard.setText(rewardsItem.getStandard());
		rewardsUnit = rewardsItem.getRewardsUnit();
		peopleSizeLimit.setText(StringUtil.valueOf(rewardsItem.getSizeLimit()));
		startTime.setText(DateTool.dateToString(rewardsItem.getStartTime()));
	
		totalJF.setText(StringUtil.valueOf(rewardsItem.getTotalJF()));

		showFrequencyInfo(rewardsItem.getFrequency());
		
//		if (rewardsItem.isAuto() == false){// 是自动奖隐藏提名
//			staffPanel.getElement().getParentElement().getParentElement()
//					.removeClassName(CssStyleConstants.hidden);
//		}else{
//			staffPanel.getElement().getParentElement().getParentElement()
//			.addClassName(CssStyleConstants.hidden);
//		}
		
		update.setVisible(false);

	}

}
