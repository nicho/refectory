/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardShop.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.awardShop.editor.AwardShopListEditor;
import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年1月20日 10:41:31
 */
public class AwardShopListEditorDescriptor implements EditorDescriptor {

	final Provider<AwardShopListEditor> editProvider;

	@Inject
	AwardShopListEditorDescriptor(Provider<AwardShopListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		AwardShopListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("奖品商城");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
