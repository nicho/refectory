package com.chinarewards.gwt.elt.client.department.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DepartmentListEditor extends AbstractEditor {

	final DepartmentListPresenter departmentListPresenter;
	Object model;

	@Inject
	protected DepartmentListEditor(DepartmentListEditorDescriptor editorDescriptor,
			DepartmentListPresenter departmentListPresenter) {
		super(editorDescriptor);
		this.departmentListPresenter = departmentListPresenter;
	}

	@Override
	public Widget asWidget() {
		return departmentListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		departmentListPresenter.unbind();
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
		
		departmentListPresenter.bind();
	}
}
