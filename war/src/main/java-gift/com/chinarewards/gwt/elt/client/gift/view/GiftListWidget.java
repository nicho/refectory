package com.chinarewards.gwt.elt.client.gift.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.elt.client.gift.presenter.GiftListPresenter.GiftListDisplay;
import com.chinarewards.gwt.elt.client.gift.view.GiftListWidget;
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

public class GiftListWidget extends Composite implements GiftListDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	
	@UiField
	Button searchBtn;
	@UiField
	Button addBtn;
	@UiField
	Button importingBtn;
	
	@UiField
	TextBox keyName;
	@UiField
	ListBox status;
	@UiField
	InlineLabel dataCount;

	
	@UiField
	Panel breadCrumbs;
	

	private static GiftWidgetUiBinder uiBinder = GWT
			.create(GiftWidgetUiBinder.class);

	interface GiftWidgetUiBinder extends UiBinder<Widget, GiftListWidget> {
	}

	public GiftListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		searchBtn.setWidth("99px");
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
	public void initGiftStatus(Map<String, String> map) {

		status.addItem("不限", "");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			status.addItem(entry.getValue(), entry.getKey());
		}
	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		addBtn.setWidth("88px");
		return addBtn;
	}

	@Override
	public HasClickHandlers getimportingBtnClickHandlers() {
		importingBtn.setWidth("88px");
		return importingBtn;
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
}
