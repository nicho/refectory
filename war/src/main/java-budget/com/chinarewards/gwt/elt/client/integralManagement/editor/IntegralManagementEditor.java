package com.chinarewards.gwt.elt.client.integralManagement.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.integralManagement.presenter.IntegralManagementPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月31日 11:22:19
 */
public class IntegralManagementEditor extends AbstractEditor {

	final IntegralManagementPresenter integralManagementPresenter;
	Object model;

	@Inject
	protected IntegralManagementEditor(IntegralManagementEditorDescriptor editorDescriptor,
			IntegralManagementPresenter integralManagementPresenter) {
		super(editorDescriptor);
		this.integralManagementPresenter = integralManagementPresenter;
	}

	@Override
	public Widget asWidget() {
		return integralManagementPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		integralManagementPresenter.unbind();
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
		integralManagementPresenter.bind();
	}
}
