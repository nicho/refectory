package com.chinarewards.gwt.elt.client.enterprise.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.enterprise.presenter.MailSetPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MailSetEditor extends AbstractEditor {

	Object model;
	MailSetPresenter mailsetPresenter;
	
	@Inject
	protected MailSetEditor(MailSetEditorDescriptor editorDescriptor,MailSetPresenter mailsetPresenter) {
		super(editorDescriptor);
		this.mailsetPresenter = mailsetPresenter;
	}


	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {
	}

	public Widget asWidget() {
		return mailsetPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		return true;
	}

	public void setModel(Object model) {
		this.model = model;
		mailsetPresenter.bind();
	}

}
