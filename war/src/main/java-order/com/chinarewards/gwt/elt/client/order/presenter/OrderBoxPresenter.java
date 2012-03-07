package com.chinarewards.gwt.elt.client.order.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public interface OrderBoxPresenter extends Presenter<OrderBoxPresenter.OrderBoxDisplay> {

	public void initOrderBox();
	public static interface OrderBoxDisplay extends Display {
		
		void setOrderNew(String text);
		void setOrderSend(String text);
			
		void setBreadCrumbs(Widget breadCrumbs);
		
		public HasClickHandlers getView() ;
		public HasClickHandlers getOperate(); 
	}
}
