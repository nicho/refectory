/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 * @since 2012年1月9日 17:25:09
 */
public class GiftEditorDescriptor implements EditorDescriptor {

	final Provider<GiftEditor> editProvider;

	@Inject
	GiftEditorDescriptor(Provider<GiftEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return GiftConstants.EDITOR_GIFT_EDIT;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		GiftEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("新建礼品");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
