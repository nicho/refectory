package com.chinarewards.gwt.elt.client.budget.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenter.CreateBudgetDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
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

public class CreateBudgetWidget extends Composite implements CreateBudgetDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	
	@UiField
	Button saveBtn;
	@UiField
	Button searchBtn;
	@UiField
	TextBox jf;
	@UiField
	ListBox year;
	@UiField
	ListBox depart;
	@UiField
	InlineLabel totalCount;
	@UiField
	InlineLabel remainCount;
	@UiField
	Panel breadCrumbs;
	
	private static OrderWidgetUiBinder uiBinder = GWT
			.create(OrderWidgetUiBinder.class);

	interface OrderWidgetUiBinder extends UiBinder<Widget, CreateBudgetWidget> {
	}

	public CreateBudgetWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSaveBtnClickHandlers() {
		return saveBtn;
	}
	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return searchBtn;
	}
	@Override
	public HasChangeHandlers getJfChangeHandlers() {
		return jf;
	}
	@Override
	public Panel getResultPanel() {
		return resultPanel;
	}


	@Override
	public HasValue<String> getJF() {
		return jf;
	}

	@Override
	public Panel getResultpage() {
		return resultpage;
	}

	@Override
	public String getYear() {
		return year.getValue(year.getSelectedIndex());
	}
	@Override
	public String getDepart() {
		return depart.getValue(depart.getSelectedIndex());
	}

	@Override
	public void initYear(Map<String, String> map) {

		
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			year.addItem(entry.getValue(), entry.getKey());
		}
	}
	
	@Override
	public void initDepart(Map<String, String> map) {

		depart.addItem("选择", "");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			depart.addItem(entry.getValue(), entry.getKey());
		}
	}


	

	@Override
	public void setTotalCount(String text) {
		totalCount.setText(text);
		
	}
	@Override
	public void setRemainCount(String text) {
		remainCount.setText(text);
		
	}
	
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);		

	}

	
	
}
