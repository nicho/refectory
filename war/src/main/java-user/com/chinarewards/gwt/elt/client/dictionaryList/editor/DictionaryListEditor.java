package com.chinarewards.gwt.elt.client.dictionaryList.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.dictionaryList.presenter.DictionaryListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DictionaryListEditor extends AbstractEditor {

	final DictionaryListPresenter dictionaryListPresenter;
	Object model;

	@Inject
	protected DictionaryListEditor(DictionaryListEditorDescriptor editorDescriptor,
			DictionaryListPresenter dictionaryListPresenter) {
		super(editorDescriptor);
		this.dictionaryListPresenter = dictionaryListPresenter;
	}

	@Override
	public Widget asWidget() {
		return dictionaryListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		dictionaryListPresenter.unbind();
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
		dictionaryListPresenter.bind();
	}
}
