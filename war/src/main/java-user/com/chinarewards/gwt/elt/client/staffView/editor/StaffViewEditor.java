package com.chinarewards.gwt.elt.client.staffView.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月15日 09:22:34
 */
public class StaffViewEditor extends AbstractEditor {

	final StaffViewPresenter StaffViewPresenter;
	Object model;

	@Inject
	protected StaffViewEditor(StaffViewEditorDescriptor editorDescriptor,
			StaffViewPresenter StaffViewPresenter) {
		super(editorDescriptor);
		this.StaffViewPresenter = StaffViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return StaffViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		StaffViewPresenter.unbind();
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
		StaffViewPresenter.initStaffView((String)model);
		StaffViewPresenter.bind();
	}
}
