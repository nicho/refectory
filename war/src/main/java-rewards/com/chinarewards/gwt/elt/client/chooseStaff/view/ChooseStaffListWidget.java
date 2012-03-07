package com.chinarewards.gwt.elt.client.chooseStaff.view;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffListPresenter.ChooseStaffListDisplay;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.StaffSpecialTextArea;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
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
import com.google.inject.Inject;

public class ChooseStaffListWidget extends Composite implements
		ChooseStaffListDisplay {

	// @UiField
	// Panel deptPanel;
	//
	@UiField
	TextBox name;

	@UiField
	Button searchBtn;

	@UiField
	Button resetBtn;

	@UiField
	Button chooseBtn;

	@UiField
	Button cancelBtn;

	@UiField
	Panel result;
	@UiField
	Panel resultpage;
	@UiField
	Panel specialBoxPanel;
	@UiField
	InlineLabel addLabel;
	
	SpecialTextArea<StaffClient> textBox;

	// is inject
	// final DepartmentComboTree deptCombo;

	interface ChooseStaffListWidgetBinder extends
			UiBinder<Widget, ChooseStaffListWidget> {
	}

	private static ChooseStaffListWidgetBinder uiBinder = GWT
			.create(ChooseStaffListWidgetBinder.class);

	@Inject
	public ChooseStaffListWidget(DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
		initSpecialTextBox();
		// this.deptCombo = new DepartmentComboTree(dispatch,
		// errorHandler,sessionManager);
		// deptPanel.add(deptCombo);
	}

	private void initSpecialTextBox() {
		textBox = new StaffSpecialTextArea();
		specialBoxPanel.add(textBox);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public HasClickHandlers getResetBtn() {
		return resetBtn;
	}

	@Override
	public HasClickHandlers getSearchBtn() {
		return searchBtn;
	}

	@Override
	public void reset() {
		name.setValue("");
		// deptCombo.setDefaultValue(null);
	}

	@Override
	public HasClickHandlers getChooseBtn() {
		return chooseBtn;
	}

	@Override
	public HasClickHandlers getCancelBtn() {
		return cancelBtn;
	}

	@Override
	public SpecialTextArea<StaffClient> getSpecialTextBox() {
		return textBox;
	}

	@Override
	public Panel getResultPanel() {
		return result;
	}

	@Override
	public void hiddenSpecialBoxPanel() {
		this.specialBoxPanel.getElement().getParentElement().addClassName(CssStyleConstants.hidden);

	}

	@Override
	public void hiddenChooseBtn() {
		this.chooseBtn.setVisible(false);

	}

	@Override
	public void setCancelBtnText(String text) {
		this.cancelBtn.setText(text);

	}

	@Override
	public Panel getResultpage() {
		return resultpage;
	}

	@Override
	public void hiddenAddLabel() {
		this.addLabel.getElement().addClassName(CssStyleConstants.hidden);
		
	}

	// @Override
	// public String getDeptId() {
	// return null;//deptCombo.getSelectedItem() != null ? deptCombo
	// //.getSelectedItem().getId() : null;
	// }

}
