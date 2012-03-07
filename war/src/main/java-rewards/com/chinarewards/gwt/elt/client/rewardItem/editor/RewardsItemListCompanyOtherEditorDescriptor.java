package com.chinarewards.gwt.elt.client.rewardItem.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class RewardsItemListCompanyOtherEditorDescriptor implements EditorDescriptor {

	final Provider<RewardsItemListCompanyOtherEditor> editProvider;

	@Inject
	RewardsItemListCompanyOtherEditorDescriptor(Provider<RewardsItemListCompanyOtherEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RewardsItemConstants.EDITOR_REWARDSITEM_COMPANYOTHER_LIST;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RewardsItemListCompanyOtherEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("公司其他奖项");
		e.setModel(model);
		return e;
	}

}
