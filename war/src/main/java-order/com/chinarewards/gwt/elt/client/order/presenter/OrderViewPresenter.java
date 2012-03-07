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

public interface OrderViewPresenter extends Presenter<OrderViewPresenter.OrderViewDisplay> {

	public void initOrderView(OrderSearchVo orderVo);
	public static interface OrderViewDisplay extends Display {


		void setTotal(String total);
		void setUnitprice(String unitprice);
		void setSource(String source);
		void setNumber(String number);
		void setName(String text);
		void setPhone(String text);
		void setAddress(String text);
		void setZipCode(String text);
	
		
		void setOrderCode(String text);
		void setExchangeDate(String text);
		void setOrderStatus(String text);
		Image getShopImage();
		
		void setOrderDefinition(String text) ;
		void setShopText(String text);
		String getUnitprice();

		HasClickHandlers getConfirmbutton();
		HasClickHandlers getReturnbutton();
		HasClickHandlers getBackbutton();
		void setNumberChange(String text);
		
		void setBreadCrumbs(Widget breadCrumbs);
		Button getConfirmbuttonObj();
	
	}
}
