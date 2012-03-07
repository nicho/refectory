package com.chinarewards.gwt.elt.client.corpBroadcast.view;

import com.chinarewards.gwt.elt.client.corpBroadcast.presenter.CorpBroadcastPresenter.CorpBroadcastDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CorpBroadcastWidget extends Composite implements
		CorpBroadcastDisplay {

	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	TextBox queryKey;
	@UiField
	Button queryBtn;
	
	@UiField
	InlineLabel titleName;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static CorpBroadcastWidgetUiBinder uiBinder = GWT
			.create(CorpBroadcastWidgetUiBinder.class);

	interface CorpBroadcastWidgetUiBinder extends
			UiBinder<Widget, CorpBroadcastWidget> {
	}

	public CorpBroadcastWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void setDataCount(String text) {
		dataCount.setText(text);

	}

	@Override
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public Panel getResultpage() {
		return this.resultpage;
	}

	@Override
	public TextBox getQueryKey() {
		return queryKey;
	}

	@Override
	public Button getQueryBtn() {
		return queryBtn;
	}

	@Override
	public void setTitleName(String text) {
		titleName.setText(text);		
	}


}
