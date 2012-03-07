/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author lw
 * @since 2011年12月9日 15:36:27
 */
public class RewardsItemViewEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsItemViewEditor> editProvider;

	@Inject
	RewardsItemViewEditorDescriptor(Provider<RewardsItemViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsItemConstants.EDITOR_REWARDSITEM_View;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsItemViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		
		String name = ((RewardsItemClient) model).getName();
		String subName = name.length() > 6 ? name.substring(0, 6) : name;
		String title =subName+ "-详细" ;
		e.setTitle(title);
	
		e.setModel(instanceId,model);
		return e;
	}

}
