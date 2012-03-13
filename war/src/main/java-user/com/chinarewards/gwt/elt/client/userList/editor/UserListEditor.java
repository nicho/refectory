package com.chinarewards.gwt.elt.client.userList.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.userList.presenter.UserListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class UserListEditor extends AbstractEditor {

	final UserListPresenter userListPresenter;
	Object model;

	@Inject
	protected UserListEditor(UserListEditorDescriptor editorDescriptor,
			UserListPresenter userListPresenter) {
		super(editorDescriptor);
		this.userListPresenter = userListPresenter;
	}

	@Override
	public Widget asWidget() {
		return userListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		userListPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model) {
		this.model = model;
		userListPresenter.bind();
	}
}
