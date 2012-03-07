package com.chinarewards.gwt.elt.client.rewards.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListStaffConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class RewardsListStaffEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsListStaffEditor> editProvider;

	@Inject
	RewardsListStaffEditorDescriptor(Provider<RewardsListStaffEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsListStaffConstants.EDITOR_REWARDSLIST_STAFF_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsListStaffEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("我的获奖列表");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
