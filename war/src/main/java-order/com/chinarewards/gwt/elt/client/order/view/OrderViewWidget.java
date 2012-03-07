package com.chinarewards.gwt.elt.client.order.view;

import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter.OrderViewDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class OrderViewWidget extends Composite implements	OrderViewDisplay {

	@UiField
	Button confirmbutton;
	@UiField
	Button returnbutton;
	@UiField
	Button backbutton;
	@UiField
	InlineLabel name;
	@UiField
	InlineLabel phone;
	@UiField
	InlineLabel address;
	@UiField
	InlineLabel zipCode;
	@UiField
	Anchor shopText;
	@UiField
	InlineLabel unitprice;
	@UiField
	InlineLabel number;
	@UiField
	Image shopImage;
	@UiField
	InlineLabel total;
	@UiField
	InlineLabel source;
		
	@UiField
	InlineLabel orderCode;
	@UiField
	InlineLabel exchangeDate;
	@UiField
	InlineLabel orderStatus;
	@UiField
	Panel breadCrumbs;
	@UiField
	TextArea orderDefinition;
	private static OrderViewWidgetUiBinder uiBinder = GWT
			.create(OrderViewWidgetUiBinder.class);

	interface OrderViewWidgetUiBinder extends
			UiBinder<Widget, OrderViewWidget> {
	}

	public OrderViewWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		this.confirmbutton.setVisible(false);
		this.backbutton.setVisible(false);
	}
	@Override
	public void setOrderDefinition(String text) {
		this. orderDefinition.setText(text);
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
	}

	@Override
	public Image getShopImage() {
		return shopImage;
	}

	@Override
	public void setShopText(String text) {
		shopText.setText(text);
		
	}

	@Override
	public String getUnitprice() {
		return unitprice.getText();
	}

	@Override
	public HasClickHandlers getConfirmbutton() {
		return confirmbutton;
	}

	@Override
	public HasClickHandlers getReturnbutton() {
		return returnbutton;
	}
	@Override
	public HasClickHandlers getBackbutton() {
		return backbutton;
	}
	@Override
	public void setNumberChange(String text) {
		 number.setText(text);
	}

	@Override
	public void setTotal(String total) {
		this.total.setText(total);		
	}

	@Override
	public void setUnitprice(String unitprice) {
		this.unitprice.setText(unitprice);
		
	}

	@Override
	public void setSource(String source) {
		this.source.setText(source);
		
	}

	@Override
	public void setNumber(String number) {
		this.number.setText(number);
		
	}

	@Override
	public Button getConfirmbuttonObj() {
		return confirmbutton;
	}

	@Override
	public void setName(String text) {
	name.setText(text);
		
	}


	@Override
	public void setPhone(String text) {
		phone.setText(text);
		
	}


	@Override
	public void setAddress(String text) {
	address.setText(text);
		
	}


	@Override
	public void setZipCode(String text) {
		zipCode.setText(text);
		
	}



	@Override
	public void setOrderCode(String text) {
		orderCode.setText(text);
		
	}



	@Override
	public void setExchangeDate(String text) {
		this.exchangeDate.setText(text);
		
	}



	@Override
	public void setOrderStatus(String text) {
		
		this.orderStatus.setText(toChineseStatus(text));
		
	}

   public String toChineseStatus(String text){
	  
	   if(text.equals("INITIAL"))
		   return "未付积分";
	   if(text.equals("NUSHIPMENTS")){
		   this.confirmbutton.setVisible(true);
		   this.backbutton.setVisible(true);
		   return "待发货";
		   
	   }
	   if(text.equals("SHIPMENTS"))
		   return "已发货";
	   if(text.equals("AFFIRM"))
		   return "确认收货";
	   else
		   return "问题定单";
   }
		


}
