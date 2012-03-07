package com.chinarewards.gwt.elt.client.staffAdd.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staffAdd.presenter.StaffAddPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class StaffAddEditor extends AbstractEditor {

	final StaffAddPresenter staffAddPresenter;
	Object model;

	@Inject
	protected StaffAddEditor(StaffAddEditorDescriptor editorDescriptor,
			StaffAddPresenter staffAddPresenter) {
		super(editorDescriptor);
		this.staffAddPresenter = staffAddPresenter;
	}

	@Override
	public Widget asWidget() {
		return staffAddPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		staffAddPresenter.unbind();
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
		if(model!=null)
			staffAddPresenter.initStaffUpdate((String)model);
		staffAddPresenter.bind();
	}
}
