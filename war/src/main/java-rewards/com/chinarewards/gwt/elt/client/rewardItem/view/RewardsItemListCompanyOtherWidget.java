package com.chinarewards.gwt.elt.client.rewardItem.view;

import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListCompanyPresenter.RewardsItemListCompanyOtherDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class RewardsItemListCompanyOtherWidget extends Composite implements
		RewardsItemListCompanyOtherDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
//	 @UiField
//	 Panel breadCrumbs;

	@UiField
	Button searchBtn;

	@UiField
	ListBox rewardsType;

	@UiField
	InlineLabel dataCount;

	private static RewardsItemListCompanyOtherWidgetUiBinder uiBinder = GWT
			.create(RewardsItemListCompanyOtherWidgetUiBinder.class);

	interface RewardsItemListCompanyOtherWidgetUiBinder extends
			UiBinder<Widget, RewardsItemListCompanyOtherWidget> {
	}

	public RewardsItemListCompanyOtherWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return searchBtn;
	}

	@Override
	public Panel getResultPanel() {
		return resultPanel;
	}

	

	@Override
	public Panel getResultpage() {
		return resultpage;
	}

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		// this.breadCrumbs.clear();
		// this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public ListBox getRewardsType() {
		return rewardsType;
	}

}
