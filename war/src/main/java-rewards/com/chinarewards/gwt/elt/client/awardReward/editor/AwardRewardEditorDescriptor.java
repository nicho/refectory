/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardReward.editor;

import com.chinarewards.gwt.elt.client.awardReward.plugin.AwardRewardConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2011年12月9日 15:36:27
 */
public class AwardRewardEditorDescriptor implements EditorDescriptor {

	final Provider<AwardRewardEditor> editProvider;

	@Inject
	AwardRewardEditorDescriptor(Provider<AwardRewardEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return AwardRewardConstants.EDITOR_AWARDREWARD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		AwardRewardEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("颁奖");
		e.setModel(instanceId,model);
		return e;
	}

}
