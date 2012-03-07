package com.chinarewards.gwt.elt.client.staffList.view;

import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPrintPresenter.StaffListPrintDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class StaffListPrintWidget extends Composite implements
		StaffListPrintDisplay {

	@UiField
	Panel resultPanel;
	@UiField
	Button printBtn;
	
	private static StaffListWidgetUiBinder uiBinder = GWT
			.create(StaffListWidgetUiBinder.class);

	interface StaffListWidgetUiBinder extends
			UiBinder<Widget, StaffListPrintWidget> {
	}

	public StaffListPrintWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public HasClickHandlers getPrintBtnClickHandlers() {
		return printBtn;
	}

}
