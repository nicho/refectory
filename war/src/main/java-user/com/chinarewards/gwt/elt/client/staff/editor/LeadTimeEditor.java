package com.chinarewards.gwt.elt.client.staff.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.chinarewards.gwt.elt.client.staff.presenter.LeadTimePresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class LeadTimeEditor extends AbstractEditor {

	Object model;
	LeadTimePresenter leatTimePresenter;

	@Inject
	protected LeadTimeEditor(
			LeadTimeEditorDescriptor editorDescriptor,
			LeadTimePresenter leatTimePresenter) {
		super(editorDescriptor);
		this.leatTimePresenter = leatTimePresenter;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {
	}

	public Widget asWidget() {
		
		return leatTimePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		return true;
	}

	public void setModel(Object model) {
		this.model = model;
		StaffVo staffVo = (StaffVo) model;
		leatTimePresenter.initEditor(staffVo.getId());
		leatTimePresenter.bind();
	}

}
