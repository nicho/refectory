package com.chinarewards.gwt.elt.client.staff.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staff.presenter.HrRegisterPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanxin
 * @since 0.0.1 2010-09-26
 */
public class HrRegisterEditor extends AbstractEditor {

	final HrRegisterPresenter hrPresenter;
	Object model;

	@Inject
	protected HrRegisterEditor(HrRegisterEditorDescriptor editorDescriptor,
			HrRegisterPresenter hrPresenter) {
		super(editorDescriptor);
		this.hrPresenter = hrPresenter;
	}

	@Override
	public Widget asWidget() {
		return hrPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		hrPresenter.unbind();
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
		hrPresenter.initRegister((String)model.toString());
		hrPresenter.bind();
	}
}
