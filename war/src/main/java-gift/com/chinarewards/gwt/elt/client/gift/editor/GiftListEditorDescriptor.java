/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftListEditor;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年1月9日 17:25:09
 */
public class GiftListEditorDescriptor implements EditorDescriptor {

	final Provider<GiftListEditor> editProvider;

	@Inject
	GiftListEditorDescriptor(Provider<GiftListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return GiftListConstants.EDITOR_GIFTLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		GiftListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("礼品列表");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
