package com.chinarewards.gwt.elt.client.rewards.view;

import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListStaffPresenter.RewardsListStaffDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class RewardsListStaffWidget extends Composite implements
		RewardsListStaffDisplay {

	@UiField
	TextBox winnerName;
	@UiField
	ListBox rewardsItem;
	@UiField
	DateBox rewardsTime;	

	@UiField
	Button searchBtn;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;

	@UiField
	InlineLabel dataCount;

	// @UiField
	// Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	private static RewardsListStaffWidgetUiBinder uiBinder = GWT
			.create(RewardsListStaffWidgetUiBinder.class);

	interface RewardsListStaffWidgetUiBinder extends
			UiBinder<Widget, RewardsListStaffWidget> {
	}

	public RewardsListStaffWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		rewardsTime.setFormat(new DateBox.DefaultFormat(dateFormat));
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
	public HasValue<String> getWinnerName() {
		return winnerName;
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
	public ListBox getRewardsItem() {
		return rewardsItem;
	}

	@Override
	public DateBox getRewardsTime() {
		return rewardsTime;
	}
}
