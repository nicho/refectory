package com.chinarewards.gwt.elt.client.enterprise.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.enterprise.presenter.EnterprisePresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class EnterpriseEditor extends AbstractEditor {

	Object model;
	EnterprisePresenter enterprisePresenter;
	
	@Inject
	protected EnterpriseEditor(EnterpriseEditorDescriptor editorDescriptor,EnterprisePresenter enterprisePresenter) {
		super(editorDescriptor);
		this.enterprisePresenter = enterprisePresenter;
	}


	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {
	}

	public Widget asWidget() {
		return enterprisePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		return true;
	}

	public void setModel(Object model) {
		this.model = model;
		enterprisePresenter.bind();
	}

}
