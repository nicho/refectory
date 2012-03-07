package com.chinarewards.gwt.elt.client.department.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.department.presenter.MergeDepartmentPresenter.MergeDepartmentDisplay;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MergeDepartmentWidget extends Composite implements
		MergeDepartmentDisplay {
	@UiField
	Hidden departmentIds;

	@UiField
	TextBox departmentName;
	@UiField
	Hidden leaderId;
	@UiField
	TextBox leaderName;

	@UiField
	Button mergeBtn;

	@UiField
	Button cancelBtn;


	interface ChooseLeaderWinWidgetBinder extends
			UiBinder<Widget, MergeDepartmentWidget> {
	}

	private static ChooseLeaderWinWidgetBinder uiBinder = GWT
			.create(ChooseLeaderWinWidgetBinder.class);

	@Inject
	public MergeDepartmentWidget(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCancelBtn() {
		return cancelBtn;
	}

	@Override
	public Hidden getDepartmentIds() {
		return departmentIds;
	}

	@Override
	public HasValue<String> getDepartmentName() {
		return departmentName;
	}

	@Override
	public Hidden getLeaderId() {
		return leaderId;
	}

	@Override
	public HasClickHandlers getMergeBtn() {
		return mergeBtn;
	}
}
