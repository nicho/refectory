package com.chinarewards.gwt.elt.client.dishesList.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.chinarewards.gwt.elt.client.dishesList.presenter.DishesListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DishesListEditor extends AbstractEditor {

	final DishesListPresenter dishesListPresenter;
	Object model;

	@Inject
	protected DishesListEditor(DictionaryListEditorDescriptor editorDescriptor,
			DishesListPresenter dishesListPresenter) {
		super(editorDescriptor);
		this.dishesListPresenter = dishesListPresenter;
	}

	@Override
	public Widget asWidget() {
		return dishesListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		dishesListPresenter.unbind();
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
		dishesListPresenter.bind();
	}
}
