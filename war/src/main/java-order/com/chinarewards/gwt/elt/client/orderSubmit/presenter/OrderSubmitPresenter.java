package com.chinarewards.gwt.elt.client.orderSubmit.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.orderSubmit.model.OrderSubmitClient;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;

public interface OrderSubmitPresenter extends Presenter<OrderSubmitPresenter.OrderSubmitDisplay> {

	public void initOrderSubmit(OrderSubmitClient orderVo);
	public static interface OrderSubmitDisplay extends Display {


		void setTotal(String total);
		void setUnitprice(String unitprice);
		void setSource(String source);
		void setNumber(String number);
		void setName(String text);
		void setPhone(String text);
		void setAddress(String text);
		void setZipCode(String text);
		void setOrderDefinition(String text);
		void setMybalance(String text);
		void setBusiness(String text);
		void setServicetell(String text);
		
		

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
		TextBox getNumberChange();
		InlineLabel getMessage();
		
		Button getConfirmbuttonObj();
		HasClickHandlers getShopText();
		void disableSpecialNote();
	}
}
