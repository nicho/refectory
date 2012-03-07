package com.chinarewards.gwt.elt.client.enterprise.presenter;

import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public interface IntegralPricePresenter extends
		Presenter<IntegralPricePresenter.IntegralPriceDisplay> {

	public static interface IntegralPriceDisplay extends Display {

		public HasClickHandlers getSaveClickHandlers();

		public HasValue<String> getIntegralPrice();

		public String getEnterpriseId();
		
		public ListBox getMoneyType();

		void setBreadCrumbs(Widget breadCrumbs);

		void initEditIntegralPrice(EnterpriseVo enterpriseVo);

		public void clear();

		public void setSaveVisible(boolean flag);
	
	}

	
	void initEditor(String id);

}
