/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author lw
 * @since 2011年12月9日 15:36:27
 */
public class RewardsItemListEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsItemListEditor> editProvider;

	@Inject
	RewardsItemListEditorDescriptor(Provider<RewardsItemListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsItemConstants.EDITOR_REWARDSITEM_List;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsItemListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("奖项列表");
		e.setModel(model);
		return e;
	}

}
