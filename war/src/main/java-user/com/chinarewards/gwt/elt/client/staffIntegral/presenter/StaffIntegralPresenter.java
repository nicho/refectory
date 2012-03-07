package com.chinarewards.gwt.elt.client.staffIntegral.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public interface StaffIntegralPresenter extends
		Presenter<StaffIntegralPresenter.StaffIntegralDisplay> {

	public static interface StaffIntegralDisplay extends Display {

		void setBreadCrumbs(Widget breadCrumbs);

		public InlineLabel getHistoryIntegral();

		public Label getConsumptionIntegral();

		public Label getBalanceIntegral();


		public void setShopWindow(Widget asWidget);
	}
}
