package com.chinarewards.gwt.elt.client.department.presenter;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MergeDepartmentWinDialog extends AbstractDialog {

	final Provider<MergeDepartmentPresenter> presenterProvider;

	MergeDepartmentPresenter presenter;

	@Inject
	public MergeDepartmentWinDialog(
			Provider<MergeDepartmentPresenter> presenterProvider) {
		super("staff.choosee", "staff.choosee");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("合并部门");

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


	public void initDialog(String departmentIds) {
		presenter.initDialog(departmentIds);
		
	}

}
