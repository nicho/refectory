package com.chinarewards.gwt.elt.client.order.view;

import com.chinarewards.gwt.elt.client.order.presenter.OrderBoxPresenter.OrderBoxDisplay;
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

public class OrderBoxWidget extends Composite implements	OrderBoxDisplay {

	
		
	@UiField
	InlineLabel news;
	@UiField
	InlineLabel send;
		@UiField
	Panel breadCrumbs;
	@UiField
	Anchor view;	
	@UiField
	Anchor operate;
	
	private static OrderBoxWidgetUiBinder uiBinder = GWT
			.create(OrderBoxWidgetUiBinder.class);

	interface OrderBoxWidgetUiBinder extends
			UiBinder<Widget, OrderBoxWidget> {
	}

	public OrderBoxWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
   
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);		

	}

	
	
	@Override
	public void setOrderNew(String text) {
		news.setText(text);
		
	}

	@Override
	public void setOrderSend(String text) {
		send.setText(text);
		
	}

	@Override
	public HasClickHandlers getView() {
		return view;
	}
	
	@Override
	public HasClickHandlers getOperate() {
		return operate;
	}

	

}
