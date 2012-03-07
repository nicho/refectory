package com.chinarewards.gwt.elt.client.staffList.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staffList.presenter.StaffListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class StaffListEditor extends AbstractEditor {

	final StaffListPresenter staffListPresenter;
	Object model;

	@Inject
	protected StaffListEditor(StaffListEditorDescriptor editorDescriptor,
			StaffListPresenter staffListPresenter) {
		super(editorDescriptor);
		this.staffListPresenter = staffListPresenter;
	}

	@Override
	public Widget asWidget() {
		return staffListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		staffListPresenter.unbind();
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
		staffListPresenter.bind();
	}
}
