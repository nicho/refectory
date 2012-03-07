package com.chinarewards.gwt.elt.client.gloryBroadcast.view;

import com.chinarewards.gwt.elt.client.gloryBroadcast.presenter.GloryBroadcastPresenter.GloryBroadcastDisplay;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class GloryBroadcastWidget extends Composite implements
		GloryBroadcastDisplay {

	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static GloryBroadcastWidgetUiBinder uiBinder = GWT
			.create(GloryBroadcastWidgetUiBinder.class);

	interface GloryBroadcastWidgetUiBinder extends
			UiBinder<Widget, GloryBroadcastWidget> {
	}

	public GloryBroadcastWidget() {
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



}
