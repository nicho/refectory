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


public class TeamListEditorDescriptor implements EditorDescriptor {

	final Provider<TeamListEditor> editProvider;

	@Inject
	TeamListEditorDescriptor(Provider<TeamListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return TeamConstants.EDITOR_TEAMLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		TeamListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("小组列表");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		
		e.setModel(model);
		return e;
	}

}
