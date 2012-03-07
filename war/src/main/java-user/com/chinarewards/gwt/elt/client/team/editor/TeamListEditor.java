package com.chinarewards.gwt.elt.client.team.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;


public class TeamListEditor extends AbstractEditor {

	final TeamListPresenter teamListPresenter;
	Object model;

	@Inject
	protected TeamListEditor(TeamListEditorDescriptor editorDescriptor,
			TeamListPresenter teamListPresenter) {
		super(editorDescriptor);
		this.teamListPresenter = teamListPresenter;
	}

	@Override
	public Widget asWidget() {
		return teamListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		teamListPresenter.unbind();
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
//		if(model instanceof TeamStatus)
//			teamListPresenter.getBoxTeam(model.toString());
		teamListPresenter.bind();
		
	}
}
