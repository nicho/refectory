package com.chinarewards.gwt.elt.client.rewardItem.view;

import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListStaffPresenter.RewardsItemListStaffDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class RewardsItemListStaffWidget extends Composite implements
		RewardsItemListStaffDisplay {

//	@UiField
//	Button searchButton;
	
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;

	@UiField
	InlineLabel dataCount;

	// @UiField
	// Panel breadCrumbs;

	private static RewardsItemListStaffWidgetUiBinder uiBinder = GWT
			.create(RewardsItemListStaffWidgetUiBinder.class);

	interface RewardsItemListStaffWidgetUiBinder extends
			UiBinder<Widget, RewardsItemListStaffWidget> {
	}

	public RewardsItemListStaffWidget() {
		initWidget(uiBinder.createAndBindUi(this));
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

//	@Override
//	public HasClickHandlers getSearchBtnClickHandlers() {		
//		return searchButton;
//	}

}
