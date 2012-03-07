package com.chinarewards.gwt.elt.client.messageSave.dialog;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MessageSaveDialog extends AbstractDialog {

	final Provider<MessageSavePresenter> presenterProvider;

	MessageSavePresenter presenter;

	@Inject
	public MessageSaveDialog(
			Provider<MessageSavePresenter> presenterProvider) {
		super("MessageSave.choosee", "MessageSave.choosee");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}
	public void initStaff(String staffId,String staffName) {
		presenter.initBroadcastStaff(staffId, staffName);
		presenter.displaySelectStaff();
		setTitle("发送悄悄话");
	}
	public void init() {
		setTitle("发送短消息");
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
