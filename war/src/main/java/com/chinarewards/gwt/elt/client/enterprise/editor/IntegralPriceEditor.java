package com.chinarewards.gwt.elt.client.enterprise.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.enterprise.presenter.IntegralPricePresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class IntegralPriceEditor extends AbstractEditor {

	Object model;
	IntegralPricePresenter enterprisePresenter;

	@Inject
	protected IntegralPriceEditor(
			IntegralPriceEditorDescriptor editorDescriptor,
			IntegralPricePresenter enterprisePresenter) {
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
		EnterpriseVo enterpriseVo = (EnterpriseVo) model;
		enterprisePresenter.initEditor(enterpriseVo.getId());
		enterprisePresenter.bind();
	}

}
