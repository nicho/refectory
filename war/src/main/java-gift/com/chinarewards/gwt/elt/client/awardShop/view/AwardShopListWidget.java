package com.chinarewards.gwt.elt.client.awardShop.view;

import com.chinarewards.gwt.elt.client.awardShop.presenter.AwardShopListPresenter.AwardShopListDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AwardShopListWidget extends Composite implements
		AwardShopListDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;

	@UiField
	Button searchBtn;

	@UiField
	TextBox keyName;

	@UiField
	InlineLabel dataCount;
	@UiField
	Panel breadCrumbs;
	private static AwardShopWidgetUiBinder uiBinder = GWT
			.create(AwardShopWidgetUiBinder.class);

	interface AwardShopWidgetUiBinder extends
			UiBinder<Widget, AwardShopListWidget> {
	}

	public AwardShopListWidget() {
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
	public HasValue<String> getKeyName() {
		return keyName;
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
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
	}
}
