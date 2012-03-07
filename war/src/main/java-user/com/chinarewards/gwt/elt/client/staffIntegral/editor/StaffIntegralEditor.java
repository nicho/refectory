package com.chinarewards.gwt.elt.client.staffIntegral.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staffIntegral.presenter.StaffIntegralPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class StaffIntegralEditor extends AbstractEditor {

	final StaffIntegralPresenter StaffIntegralPresenter;
	Object model;

	@Inject
	protected StaffIntegralEditor(StaffIntegralEditorDescriptor editorDescriptor,
			StaffIntegralPresenter StaffIntegralPresenter) {
		super(editorDescriptor);
		this.StaffIntegralPresenter = StaffIntegralPresenter;
	}

	@Override
	public Widget asWidget() {
		return StaffIntegralPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		StaffIntegralPresenter.unbind();
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
		StaffIntegralPresenter.bind();
	}
}
