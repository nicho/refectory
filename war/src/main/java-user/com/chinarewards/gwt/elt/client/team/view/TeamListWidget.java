package com.chinarewards.gwt.elt.client.team.view;

import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenter.TeamListDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TeamListWidget extends Composite implements TeamListDisplay {
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	
	@UiField
	Button searchBtn;
	@UiField
	Button addBtn;
	@UiField
	TextBox keyName;
	
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel breadCrumbs;
	
	private static TeamWidgetUiBinder uiBinder = GWT
			.create(TeamWidgetUiBinder.class);

	interface TeamWidgetUiBinder extends UiBinder<Widget, TeamListWidget> {
	}

	public TeamListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return searchBtn;
	}
	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
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
	public void setDataCount(String text) {
		dataCount.setText(text);
		
	}
	
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);		

	}
	
}
