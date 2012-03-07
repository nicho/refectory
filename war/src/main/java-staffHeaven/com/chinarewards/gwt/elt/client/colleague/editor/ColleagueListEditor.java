package com.chinarewards.gwt.elt.client.colleague.editor;

import com.chinarewards.gwt.elt.client.colleague.presenter.ColleagueListPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class ColleagueListEditor extends AbstractEditor {

	final ColleagueListPresenter colleagueListPresenter;
	Object model;

	@Inject
	protected ColleagueListEditor(ColleagueListEditorDescriptor editorDescriptor,
			ColleagueListPresenter colleagueListPresenter) {
		super(editorDescriptor);
		this.colleagueListPresenter = colleagueListPresenter;
	}

	@Override
	public Widget asWidget() {
		return colleagueListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		colleagueListPresenter.unbind();
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
		colleagueListPresenter.bind();
	}
}
