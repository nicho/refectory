package com.chinarewards.gwt.elt.client.department.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DepartmentEditor extends AbstractEditor {

	final DepartmentPresenter departmentPresenter;
	Object model;

	@Inject
	protected DepartmentEditor(DepartmentEditorDescriptor editorDescriptor,
			DepartmentPresenter departmentPresenter) {
		super(editorDescriptor);
		this.departmentPresenter = departmentPresenter;
	}

	@Override
	public Widget asWidget() {
		return departmentPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		departmentPresenter.unbind();
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
		DepartmentClient departmentClient = (DepartmentClient) model;
		departmentPresenter
				.initEditor(departmentClient.getId(), departmentClient.getThisAction());
		departmentPresenter.bind();
	}
}
