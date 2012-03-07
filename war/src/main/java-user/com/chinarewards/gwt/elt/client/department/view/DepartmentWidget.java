package com.chinarewards.gwt.elt.client.department.view;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.department.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentConstants;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentPresenter.DepartmentDisplay;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.OrganizationSpecialTextArea;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class DepartmentWidget extends Composite implements DepartmentDisplay {

	@UiField
	Label departmentNameLabel;
	@UiField
	TextBox departmentName;

	@UiField
	Hidden departmentId;

	@UiField
	Hidden parentId;
	@UiField
	Label parentName;
	@UiField
	Label childdepartment;
	@UiField
	Label peopleNumber;
	@UiField
	Label yearintegral;
	@UiField
	Label issueintegral;
	@UiField
	Label procesRewarditemCount;

	// leader
	@UiField
	Panel leaderPanel;
	@UiField
	Panel preLeaderPanel;	

	SpecialTextArea<OrganicationClient> leaderArea;	
	SpecialTextArea<OrganicationClient> preLeaderArea;

	@UiField
	Button chooseLeaderBtn;

	@UiField
	Button save;

	@UiField
	Button back;

	@UiField
	Panel breadCrumbs;

	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface DepartmentWidgetBinder extends UiBinder<Widget, DepartmentWidget> {

	}

	private static DepartmentWidgetBinder uiBinder = GWT
			.create(DepartmentWidgetBinder.class);

	@Inject
	public DepartmentWidget(DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditDepartment(DepartmentVo departmentVo) {
		// System.out.println("----widget  initEditDepartment:"
		// + departmentVo.getId());
		departmentId.setValue(departmentVo.getId());

		departmentName.setText(departmentVo.getName());
		
		String thisAction = departmentVo.getThisAction();
		if (DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT.equals(thisAction)) {
			// departmentName.setVisible(false);
			departmentName.setEnabled(false);
		}

		departmentNameLabel.setText(departmentVo.getName());

		leaderArea = new OrganizationSpecialTextArea();
		preLeaderArea = new OrganizationSpecialTextArea();
		
		List<OrganicationClient> leaderList = departmentVo.getLeaderList();
		for (int i = 0; i < leaderList.size(); i++) {
			OrganicationClient leader=leaderList.get(i);
			leaderArea.addItem(leader);
			preLeaderArea.addItem(leader);
		}
		leaderPanel.clear();
		leaderPanel.add(leaderArea);// leader面板
		
		preLeaderPanel.clear();
		preLeaderPanel.add(preLeaderArea);// leader面板
		

		initStatsInfo(departmentVo);

		// System.out.println("-------initDepartmentWidget===:"+departmentVo.getLeaderId());
	}

	private void initStatsInfo(DepartmentVo departmentVo) {
		parentId.setValue(departmentVo.getParentId());
		parentName
				.setText(getDepartmentParentName(departmentVo.getParentName()));

		// private String childdeparmentNames;

		String childNames = "";
		List<String> childList = departmentVo.getChildNames();
		if (childList != null) {
			for (int i = 0; i < childList.size(); i++) {
				childNames += childList.get(i) + ",";
			}
			int subIndex = childNames.lastIndexOf(",");
			if (subIndex > 0) {
				childNames = childNames.substring(0, subIndex);
			}

			childdepartment.setText(childNames);
		}

		peopleNumber.setText(departmentVo.getPeopleNumber());
		yearintegral.setText(departmentVo.getYearintegral());
		issueintegral.setText(departmentVo.getIssueintegral());
	}

	@Override
	public void initAddDepartment(DepartmentVo departmentVo) {
		departmentId.setValue(departmentVo.getId());

		leaderArea = new OrganizationSpecialTextArea();
		leaderPanel.add(leaderArea);//
	}

	@Override
	public void initSaveSameLevelDepartment(DepartmentVo departmentVo) {
		parentId.setValue(departmentVo.getParentId());
		parentName
				.setText(getDepartmentParentName(departmentVo.getParentName()));

		leaderArea = new OrganizationSpecialTextArea();
		leaderPanel.add(leaderArea);//
	}

	@Override
	public void initSaveChildDepartment(DepartmentVo departmentVo) {
		parentId.setValue(departmentVo.getId());
		parentName.setText(departmentVo.getName());

		leaderArea = new OrganizationSpecialTextArea();
		leaderPanel.add(leaderArea);//

	}

	private static String getDepartmentParentName(String parentName) {
		if (parentName != null) {
			if (parentName.indexOf("ROOT_DEPT") > -1) {
				return "";
			}
		}
		return parentName;
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public void clear() {

	}

	@Override
	public HasClickHandlers getSaveClick() {
		return save;
	}

	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	@Override
	public Label getChilddepartment() {
		return childdepartment;
	}

	@Override
	public Label getPeopleNumber() {
		return peopleNumber;
	}

	@Override
	public Label getYearintegral() {
		return yearintegral;
	}

	@Override
	public Label getIssueintegral() {
		return issueintegral;
	}

	@Override
	public HasValue<String> getDepartmentName() {
		return departmentName;
	}

	@Override
	public Hidden getParentId() {
		return parentId;
	}

	@Override
	public Hidden getDepartmentId() {
		return departmentId;
	}

	@Override
	public HasClickHandlers getChooseLeaderBtnClick() {
		return chooseLeaderBtn;
	}

	@Override
	public SpecialTextArea<OrganicationClient> getLeaderArea() {
		return leaderArea;
	}

	@Override
	public Label getProcesRewarditemCount() {
		return procesRewarditemCount;
	}

	@Override
	public SpecialTextArea<OrganicationClient> getPreLeaderArea() {
		return preLeaderArea;
	}

}
