package com.chinarewards.gwt.elt.client.box.editor;

import com.chinarewards.gwt.elt.client.box.presenter.UserBoxPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:35:29
 */
public class UserBoxEditor extends AbstractEditor {

	final UserBoxPresenter userBoxPresenter;
	Object model;

	@Inject
	protected UserBoxEditor(UserBoxEditorDescriptor editorDescriptor,
			UserBoxPresenter userBoxPresenter) {
		super(editorDescriptor);
		this.userBoxPresenter = userBoxPresenter;
	}

	@Override
	public Widget asWidget() {
		return userBoxPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		userBoxPresenter.unbind();
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
		userBoxPresenter.initUserBox();
		userBoxPresenter.bind();
	}
}
