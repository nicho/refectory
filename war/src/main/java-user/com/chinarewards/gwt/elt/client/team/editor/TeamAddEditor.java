package com.chinarewards.gwt.elt.client.team.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.team.presenter.TeamAddPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;


public class TeamAddEditor extends AbstractEditor {

	final TeamAddPresenter teamAddPresenter;
	Object model;

	@Inject
	protected TeamAddEditor(TeamAddEditorDescriptor editorDescriptor,
			TeamAddPresenter teamAddPresenter) {
		super(editorDescriptor);
		this.teamAddPresenter = teamAddPresenter;
	}

	@Override
	public Widget asWidget() {
		return teamAddPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		teamAddPresenter.unbind();
		return true;
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(String instanceId,Object model) {
		this.model = model;
		if (model != null) {
			if (model instanceof TeamSearchVo)
				teamAddPresenter.initEditor(instanceId,(TeamSearchVo) model);
			else
				teamAddPresenter.initEditor(instanceId,null);
		}
		
		teamAddPresenter.bind();
		
	}
}
