package com.chinarewards.gwt.elt.client.chooseStaff.view;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffPanelPresenter.ChooseStaffPanelDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.view.OrganizationSpecialTextArea;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 选择员工
 * 
 * @author nicho
 * 
 */
public class ChooseStaffPanelWidget extends Composite implements
		ChooseStaffPanelDisplay {

	@UiField
	Button chooseBtn;

	@UiField
	Panel staffTextAreaPanel;
	@UiField
	HTMLPanel select1;

	@UiField
	InlineLabel topName;

	SpecialTextArea<OrganicationClient> staffTextArea;

	/**
	 * 符合条件的员工集合 StaffReacher and DepartmentReacher
	 */
	// Map<String, OrganicationClient> staffMap = new HashMap<String,
	// OrganicationClient>();

	public ChooseStaffPanelWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	interface ChooseStaffPanelBinder extends
			UiBinder<Widget, ChooseStaffPanelWidget> {
	}

	private static ChooseStaffPanelBinder uiBinder = GWT
			.create(ChooseStaffPanelBinder.class);

	void init() {

		staffTextArea = new OrganizationSpecialTextArea();
		staffTextAreaPanel.add(staffTextArea);

	}

	/*
	 * @Override public Map<String, OrganicationClient> getStaffMap() { return
	 * staffMap; }
	 */
	@Override
	public SpecialTextArea<OrganicationClient> getSpecialTextArea() {
		return staffTextArea;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public List<String[]> getRealOrginzationIds() {
		List<String[]> realOrginzationIds = new ArrayList<String[]>();
		List<OrganicationClient> existKeys = staffTextArea.getItemList();
		for (OrganicationClient key : existKeys) {
			String[] nameAndId = new String[2];
			nameAndId[0] = key.getId();
			nameAndId[1] = key.getName();
			realOrginzationIds.add(nameAndId);
		}
		return realOrginzationIds;
	}

	@Override
	public HasClickHandlers getChooseStaffBtnClick() {
		return chooseBtn;
	}

	@Override
	public void setTopName(String topName) {
		this.topName.setText(topName);

	}

	@Override
	public void setChooseBtnName(String name) {
		this.chooseBtn.setText(name);

	}

	@Override
	public void cleanStaffTextAreaPanel() {
		this.staffTextAreaPanel.clear();
		this.staffTextAreaPanel.getElement().addClassName(CssStyleConstants.hidden);
		
	}

}
