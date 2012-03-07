package com.chinarewards.gwt.elt.client.budget.editor;

import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;


public class CreateBudgetEditor extends AbstractEditor {

	final CreateBudgetPresenter createBudgetPresenter;
	Object model;

	@Inject
	protected CreateBudgetEditor(CreateBudgetEditorDescriptor editorDescriptor,
			CreateBudgetPresenter createBudgetPresenter) {
		super(editorDescriptor);
		this.createBudgetPresenter = createBudgetPresenter;
	}

	@Override
	public Widget asWidget() {
		return createBudgetPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		createBudgetPresenter.unbind();
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
		
		createBudgetPresenter.bind();
		
	}
}
