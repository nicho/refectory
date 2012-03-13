package com.chinarewards.gwt.elt.client.dishesTypeList.view;


import com.chinarewards.gwt.elt.client.dishesTypeList.presenter.DishesTypeListPresenter.DishesTypeListDisplay;
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

public class DishesTypeListWidget extends Composite implements DishesTypeListDisplay {

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
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	@UiField
	Panel breadCrumbs;
	
	private static DishesTypeListWidgetUiBinder uiBinder = GWT
			.create(DishesTypeListWidgetUiBinder.class);

	interface DishesTypeListWidgetUiBinder extends
			UiBinder<Widget, DishesTypeListWidget> {
	}

	public DishesTypeListWidget() {
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
	}


}
