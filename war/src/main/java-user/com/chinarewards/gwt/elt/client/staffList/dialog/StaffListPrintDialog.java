package com.chinarewards.gwt.elt.client.staffList.dialog;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPrintPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class StaffListPrintDialog extends AbstractDialog {

	final Provider<StaffListPrintPresenter> presenterProvider;

	StaffListPrintPresenter presenter;

	@Inject
	public StaffListPrintDialog(
			Provider<StaffListPrintPresenter> presenterProvider) {
		super("Organization.choosee", "Organization.choosee");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("全部员工帐号");
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
