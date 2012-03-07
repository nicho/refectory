package com.chinarewards.gwt.elt.client.order.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.elt.client.order.view.OrderListWidget;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.presenter.OrderListPresenter.OrderListDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OrderListWidget extends Composite implements OrderListDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	
	@UiField
	Button searchBtn;
	
	@UiField
	TextBox keyName;
	@UiField
	ListBox status;
	@UiField
	ListBox source;
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel breadCrumbs;
	
	private static OrderWidgetUiBinder uiBinder = GWT
			.create(OrderWidgetUiBinder.class);

	interface OrderWidgetUiBinder extends UiBinder<Widget, OrderListWidget> {
	}

	public OrderListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return searchBtn;
	}

	@Override
	public Panel getResultPanel() {
		return resultPanel;
	}


	@Override
	public HasValue<String> getKeyName() {
		return keyName;
	}

	@Override
	public Panel getResultpage() {
		return resultpage;
	}

	@Override
	public String getStatus() {
		return status.getValue(status.getSelectedIndex());
	}
	@Override
	public String getSource() {
		return source.getValue(source.getSelectedIndex());
	}

	@Override
	public void initOrderStatus(Map<String, String> map) {

		status.addItem("不限", "");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			status.addItem(entry.getValue(), entry.getKey());
		}
	}
	
	@Override
	public void initOrderSource(Map<String, String> map) {

		source.addItem("不限", "");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			source.addItem(entry.getValue(), entry.getKey());
		}
	}


	

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}
	
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);		

	}

	@Override
	public void setStatus(String statu) {
		if(statu.equals(OrderStatus.INITIAL.toString()))
		  status.setSelectedIndex(5);
		else if(statu.equals(OrderStatus.NUSHIPMENTS.toString()))
		  status.setSelectedIndex(4);	
//		else
//			 status.setSelectedIndex(0);
	}
}
