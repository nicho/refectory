package com.chinarewards.gwt.elt.client.staffHeavenIndex.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter.StaffHeavenIndexPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class StaffHeavenIndexEditor extends AbstractEditor {

	final StaffHeavenIndexPresenter staffHeavenIndexPresenter;
	Object model;

	@Inject
	protected StaffHeavenIndexEditor(StaffHeavenIndexEditorDescriptor editorDescriptor,
			StaffHeavenIndexPresenter staffHeavenIndexPresenter) {
		super(editorDescriptor);
		this.staffHeavenIndexPresenter = staffHeavenIndexPresenter;
	}

	@Override
	public Widget asWidget() {
		return staffHeavenIndexPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		staffHeavenIndexPresenter.unbind();
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
		staffHeavenIndexPresenter.bind();
	}
}
