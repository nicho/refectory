package com.chinarewards.gwt.elt.client.orderHistory.view;

import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryViewPresenter.OrderHistoryViewDisplay;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class OrderHistoryViewWidget extends Composite implements
		OrderHistoryViewDisplay {
	@UiField
	Button confirmbutton;
	@UiField
	Button returnbutton;
	@UiField
	Button receivebutton;
	@UiField
	Label orderCode;
	@UiField
	InlineLabel exchangeDate;
	@UiField
	InlineLabel statusText;

	@UiField
	InlineLabel receiver;
	@UiField
	InlineLabel tel;
	@UiField
	InlineLabel address;
	@UiField
	InlineLabel postcode;
	
	@UiField
	Anchor shopText;
	@UiField
	InlineLabel unitprice;
	@UiField
	Label number;
	@UiField
	Image shopImage;
	@UiField
	InlineLabel total;
	@UiField
	InlineLabel source;
	@UiField
	InlineLabel message;
	@UiField
	InlineLabel mybalance;
	@UiField
	TextArea orderDefinition;
	@UiField
	InlineLabel business;
	@UiField
	InlineLabel servicetell;
	
	private static OrderHistoryViewWidgetUiBinder uiBinder = GWT
			.create(OrderHistoryViewWidgetUiBinder.class);

	interface OrderHistoryViewWidgetUiBinder extends
			UiBinder<Widget, OrderHistoryViewWidget> {
	}

	public OrderHistoryViewWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		this.confirmbutton.setVisible(false);
		this.receivebutton.setVisible(false);
	}

	@Override
	public void showOrderHistory(OrderViewClient orderVo) {
		orderCode.setText(orderVo.getOrdercode());
		exchangeDate.setText(DateTool.dateToString(orderVo.getExchangeDate()));
		business.setText(orderVo.getBussiness());
		servicetell.setText(orderVo.getServicetell());
		statusText.setText(toChineseStatus(orderVo.getOrderStatus()));
		orderDefinition.setText(orderVo.getOrderDefinition());
		receiver.setText(orderVo.getName());
		tel.setText(orderVo.getPhone());

		address.setText(orderVo.getAddress());
		postcode.setText(orderVo.getZipCode());
		
		shopImage.setUrl("imageshow?imageName="+orderVo.getGiftImage());
		shopText.setText(orderVo.getGiftName());
		total.setText(orderVo.getTotal()+"");
		unitprice.setText(orderVo.getIntegral()+"");
		source.setText(orderVo.getSource());
		number.setText(orderVo.getNumber()+"");
		mybalance.setText(orderVo.getUserBalance()+"");
		if(orderVo.getUserBalance()<orderVo.getIntegral())
		{
			this.message.setVisible(true);
			this.confirmbutton.setEnabled(false);
		}
		else
		{
			this.message.setVisible(false);
			this.confirmbutton.setEnabled(true);
		}
	}
	 public String toChineseStatus(String text){
		  
		   if(text.equals("INITIAL")){
			   this.confirmbutton.setVisible(true);
			   return "未付积分";
		   }
		   if(text.equals("NUSHIPMENTS"))
			   return "待发货";
		   if(text.equals("SHIPMENTS")){
			   this.receivebutton.setVisible(true);
			   return "已发货";
		   }
		   if(text.equals("AFFIRM"))
			   return "确认收货";
		   else
			   return "问题定单";
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
	public HasClickHandlers getReceivebutton() {
		return receivebutton;
	}

	

}
