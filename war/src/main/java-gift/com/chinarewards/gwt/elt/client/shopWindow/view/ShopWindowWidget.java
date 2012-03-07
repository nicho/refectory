package com.chinarewards.gwt.elt.client.shopWindow.view;

import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenter.ShopWindowDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ShopWindowWidget extends Composite implements
		ShopWindowDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	InlineLabel pageTitle;
	@UiField
	Anchor more;
	
	private static AwardShopWidgetUiBinder uiBinder = GWT
			.create(AwardShopWidgetUiBinder.class);

	interface AwardShopWidgetUiBinder extends
			UiBinder<Widget, ShopWindowWidget> {
	}

	public ShopWindowWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Panel getResultPanel() {
		return resultPanel;
	}
	@Override
	public void setPageTitle(String text) {
		pageTitle.setText(text);
	}

	@Override
	public HasClickHandlers getMore() {
		return more;
	}
	



}
