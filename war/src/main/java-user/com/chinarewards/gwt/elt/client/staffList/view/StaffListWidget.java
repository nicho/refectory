package com.chinarewards.gwt.elt.client.staffList.view;


import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter.StaffListDisplay;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
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

public class StaffListWidget extends Composite implements StaffListDisplay {

	@UiField
	TextBox staffNameorNo;
	@UiField
	ListBox staffStatus;
	@UiField
	ListBox staffRole;
	
	@UiField
	Button addStaffBtn;
	@UiField
	Button synchronousStaffBtn;
	@UiField
	Button searchBtn;
	@UiField
	Button createSysUserBtn;
	@UiField
	Button printBtn;
	
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel breadCrumbs;
	
	private static StaffListWidgetUiBinder uiBinder = GWT
			.create(StaffListWidgetUiBinder.class);

	interface StaffListWidgetUiBinder extends
			UiBinder<Widget, StaffListWidget> {
	}

	public StaffListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getSearchBtnClickHandlers() {
		return this.searchBtn;
	}

	@Override
	public HasClickHandlers getSynchronousStaffBtnClickHandlers() {
		return this.synchronousStaffBtn;
	}

	@Override
	public HasClickHandlers getAddStaffBtnClickHandlers() {
		return this.addStaffBtn;
	}

	@Override
	public void initStaffStatus() {
		staffStatus.addItem("不限", "ALL");
		staffStatus.addItem("待入职", "ENTRY");
		staffStatus.addItem("在职", "JOB");
		staffStatus.addItem("已离职", "DEPARTURE");
		
		staffRole.addItem("不限", "ALL");
		staffRole.addItem("HR管理员", UserRoleVo.CORP_ADMIN.toString());
		staffRole.addItem("部门管理员", UserRoleVo.DEPT_MGR.toString());
		staffRole.addItem("礼品管理员", UserRoleVo.GIFT.toString());
		staffRole.addItem("普通员工", UserRoleVo.STAFF.toString());
		
		
	}

	@Override
	public HasValue<String> getStaffNameorNo() {
		return this.staffNameorNo;
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
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public Panel getResultpage() {
		return this.resultpage;
	}

	@Override
	public String getSttaffStatus() {
		return staffStatus.getValue(staffStatus.getSelectedIndex());
	}
	@Override
	public String getSttaffRole() {
		return staffRole.getValue(staffRole.getSelectedIndex());
	}

	@Override
	public void displayBtn() {
		addStaffBtn.setVisible(false);
		synchronousStaffBtn.setVisible(false);
		createSysUserBtn.setVisible(false);
		printBtn.setVisible(false);
	}

	@Override
	public HasClickHandlers getCreateSysUserBtnClickHandlers() {
		return createSysUserBtn;
	}

	@Override
	public HasClickHandlers getPrintBtnClickHandlers() {
		return printBtn;
	}


}
