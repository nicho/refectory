package com.chinarewards.gwt.elt.client.staffIntegral.view;

import com.chinarewards.gwt.elt.client.staffIntegral.presenter.StaffIntegralPresenter.StaffIntegralDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class StaffIntegralWidget extends Composite implements
		StaffIntegralDisplay {

	@UiField
	InlineLabel historyIntegral;
	@UiField
	InlineLabel consumptionIntegral;
	@UiField
	InlineLabel balanceIntegral;

//	@UiField
//	Panel breadCrumbs;
	
	@UiField
	Panel shopWindow;

	private static StaffIntegralWidgetUiBinder uiBinder = GWT
			.create(StaffIntegralWidgetUiBinder.class);

	interface StaffIntegralWidgetUiBinder extends
			UiBinder<Widget, StaffIntegralWidget> {
	}

	public StaffIntegralWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
//		this.breadCrumbs.clear();
//		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public InlineLabel getHistoryIntegral() {
		return historyIntegral;
	}

	@Override
	public Label getConsumptionIntegral() {	
		return consumptionIntegral;
	}


	@Override
	public Label getBalanceIntegral() {
		return balanceIntegral;
	}

	@Override
	public void setShopWindow(Widget asWidget) {
		this.shopWindow.clear();
		this.shopWindow.add(asWidget);
	}

}
