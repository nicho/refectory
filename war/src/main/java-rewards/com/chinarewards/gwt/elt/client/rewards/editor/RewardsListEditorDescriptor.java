/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2011年12月9日 15:36:27
 */
public class RewardsListEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsListEditor> editProvider;

	@Inject
	RewardsListEditorDescriptor(Provider<RewardsListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsListConstants.EDITOR_REWARDSLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("奖励列表");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
