/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfGift.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.detailsOfGift.plugin.DetailsOfGiftConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月1日 13:36:10
 */
public class DetailsOfGiftEditorDescriptor implements EditorDescriptor {

	final Provider<DetailsOfGiftEditor> editProvider;

	@Inject
	DetailsOfGiftEditorDescriptor(Provider<DetailsOfGiftEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DetailsOfGiftConstants.EDITOR_DETAILSOFGIFT_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DetailsOfGiftEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("礼品详细");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
