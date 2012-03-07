package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class RewardsItemListStaffEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsItemListStaffEditor> editProvider;

	@Inject
	RewardsItemListStaffEditorDescriptor(Provider<RewardsItemListStaffEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsItemConstants.EDITOR_REWARDSITEM_STAFF_LIST;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsItemListStaffEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("我参与的奖项");
		e.setModel(model);
		return e;
	}

}
