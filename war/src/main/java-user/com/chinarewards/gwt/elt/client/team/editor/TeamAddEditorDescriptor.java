/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.team.plugin.TeamConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class TeamAddEditorDescriptor implements EditorDescriptor {

	final Provider<TeamAddEditor> editProvider;

	@Inject
	TeamAddEditorDescriptor(Provider<TeamAddEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return TeamConstants.EDITOR_TEAM_ADD;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		TeamAddEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("增加小组");
		e.setModel(instanceId,model);
		return e;
	}

}
