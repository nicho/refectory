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
public class RewardsItemStoreListEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsItemStoreListEditor> editProvider;

	@Inject
	RewardsItemStoreListEditorDescriptor(Provider<RewardsItemStoreListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsItemConstants.EDITOR_REWARDSITEMSTORE_LIST;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsItemStoreListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("奖项库列表");
		e.setModel(model);
		return e;
	}

}
