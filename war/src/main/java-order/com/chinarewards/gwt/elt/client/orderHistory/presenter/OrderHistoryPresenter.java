package com.chinarewards.gwt.elt.client.orderHistory.presenter;


import java.util.Date;
import java.util.Map;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;

public interface OrderHistoryPresenter extends Presenter<OrderHistoryPresenter.OrderHistoryDisplay> {


	public static interface OrderHistoryDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		void setDataCount(String text);
		public HasValue<Date> getCreateTime();
		public HasValue<Date> getCreateTimeEnd();
		String getStatus();
		Panel getResultPanel();
		Panel getResultpage();
		public void initOrderStatus(Map<String, String> map);
		
		
	}
}
