package com.chinarewards.gwt.elt.client.broadcastSave.dialog;

import com.chinarewards.gwt.elt.client.chooseOrganization.presenter.ChooseOrganizationListPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChooseOrganizationListDialog extends AbstractDialog {

	final Provider<ChooseOrganizationListPresenter> presenterProvider;

	ChooseOrganizationListPresenter presenter;

	@Inject
	public ChooseOrganizationListDialog(
			Provider<ChooseOrganizationListPresenter> presenterProvider) {
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
