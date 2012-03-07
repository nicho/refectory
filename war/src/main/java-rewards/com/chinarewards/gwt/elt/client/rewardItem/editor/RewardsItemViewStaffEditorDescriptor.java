package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class RewardsItemViewStaffEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsItemViewStaffEditor> editProvider;

	@Inject
	RewardsItemViewStaffEditorDescriptor(Provider<RewardsItemViewStaffEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsItemConstants.EDITOR_REWARDSITEM_VIEW_STAFF;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsItemViewStaffEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		
		e.setTitle("查看奖项");
	
		e.setModel(model);
		return e;
	}

}
