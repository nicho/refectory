package com.chinarewards.gwt.elt.client.orderHistory.view;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenter.OrderHistoryDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class OrderHistoryWidget extends Composite implements OrderHistoryDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	
	@UiField
	Button searchBtn;
	
	@UiField
	DateBox createTime;
	@UiField
	DateBox createTimeEnd;
	
	@UiField
	ListBox status;
	
	@UiField
	InlineLabel dataCount;
	
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(ViewConstants.date_format);
	private static OrderWidgetUiBinder uiBinder = GWT
			.create(OrderWidgetUiBinder.class);

	interface OrderWidgetUiBinder extends UiBinder<Widget, OrderHistoryWidget> {
	}

	public OrderHistoryWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		createTime.setFormat(new DateBox.DefaultFormat(dateFormat));
		createTimeEnd.setFormat(new DateBox.DefaultFormat(dateFormat));
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
	public HasValue<Date> getCreateTime() {
		return createTime;
	}
	
	@Override
	public HasValue<Date> getCreateTimeEnd() {
		return createTimeEnd;
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
	public void initOrderStatus(Map<String, String> map) {

		status.addItem("不限", "");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			status.addItem(entry.getValue(), entry.getKey());
		}
	}
	
	


	

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}
	
	
}
