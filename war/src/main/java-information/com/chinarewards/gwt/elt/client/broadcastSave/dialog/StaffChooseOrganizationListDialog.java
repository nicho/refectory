package com.chinarewards.gwt.elt.client.broadcastSave.dialog;

import com.chinarewards.gwt.elt.client.chooseOrganization.presenter.StaffChooseOrganizationListPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class StaffChooseOrganizationListDialog extends AbstractDialog {

	final Provider<StaffChooseOrganizationListPresenter> presenterProvider;

	StaffChooseOrganizationListPresenter presenter;

	@Inject
	public StaffChooseOrganizationListDialog(
			Provider<StaffChooseOrganizationListPresenter> presenterProvider) {
		super("Organization.choosee", "Organization.choosee");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("选择员工/部门/小组");
		presenter.bind();
	}

	@Override
	public Widget asWidget() {
		return presenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		presenter.unbind();
		return true;
	}
//
//	public void initChooseList(InitChooseListParam initChooseListParam) {
//		presenter.initChooseList(initChooseListParam);
//	}
//
//	public void setOrganizationOnly(boolean OrganizationOnly) {
//		presenter.setOrganizationOnly(OrganizationOnly);
//	}
}
