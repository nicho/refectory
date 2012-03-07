package com.chinarewards.gwt.elt.client.staffInfo.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staffInfo.presenter.StaffInfoPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月15日 09:22:34
 */
public class StaffInfoEditor extends AbstractEditor {

	final StaffInfoPresenter StaffInfoPresenter;
	Object model;

	@Inject
	protected StaffInfoEditor(StaffInfoEditorDescriptor editorDescriptor,
			StaffInfoPresenter StaffInfoPresenter) {
		super(editorDescriptor);
		this.StaffInfoPresenter = StaffInfoPresenter;
	}

	@Override
	public Widget asWidget() {
		return StaffInfoPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		StaffInfoPresenter.unbind();
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
		StaffInfoPresenter.bind();
	}
}
