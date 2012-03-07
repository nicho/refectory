package com.chinarewards.gwt.elt.client.hrbox.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.hrbox.presenter.HrBoxPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:35:29
 */
public class HrBoxEditor extends AbstractEditor {

	final HrBoxPresenter hrBoxPresenter;
	Object model;

	@Inject
	protected HrBoxEditor(HrBoxEditorDescriptor editorDescriptor,
			HrBoxPresenter hrBoxPresenter) {
		super(editorDescriptor);
		this.hrBoxPresenter = hrBoxPresenter;
	}

	@Override
	public Widget asWidget() {
		return hrBoxPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		hrBoxPresenter.unbind();
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
		hrBoxPresenter.initHrBox();
		hrBoxPresenter.bind();
	}
}
