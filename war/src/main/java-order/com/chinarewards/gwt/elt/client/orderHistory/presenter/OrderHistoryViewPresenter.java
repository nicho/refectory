package com.chinarewards.gwt.elt.client.orderHistory.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface OrderHistoryViewPresenter extends
		Presenter<OrderHistoryViewPresenter.OrderHistoryViewDisplay> {

	public static interface OrderHistoryViewDisplay extends Display {
		public void showOrderHistory(OrderViewClient orderVo);

		HasClickHandlers getConfirmbutton();

		HasClickHandlers getReturnbutton();
		HasClickHandlers getReceivebutton();

	

	}

	public void initInstanceId(String instanceId, OrderSearchVo model);
}
