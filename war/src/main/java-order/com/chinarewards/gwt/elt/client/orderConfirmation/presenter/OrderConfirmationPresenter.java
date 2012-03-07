package com.chinarewards.gwt.elt.client.orderConfirmation.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;

public interface OrderConfirmationPresenter extends Presenter<OrderConfirmationPresenter.OrderConfirmationDisplay> {

	public void initOrderConfirmation(String giftId);
	public static interface OrderConfirmationDisplay extends Display {


		void setTotal(String total);
		void setUnitprice(String unitprice);
		void setSource(String source);
		void setNumber(String number);
		void setMybalance(String mybalance);

		HasValue<String> getName();
		HasValue<String> getPhone();
		HasValue<String> getAddress();
		HasValue<String> getZipCode();
		HasValue<String> getNumber();
		HasValue<String> getOrderDefinition();
		Image getShopImage();
		
		
		void setShopText(String text);
		String getUnitprice();

		HasClickHandlers getConfirmbutton();
		HasClickHandlers getReturnbutton();
		HasClickHandlers getShopText();
		TextBox getNumberChange();
		InlineLabel getMessage();
		
		Button getConfirmbuttonObj();
	
	}
}
