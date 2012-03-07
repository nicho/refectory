/**
 * 
 */
package com.chinarewards.gwt.elt.client.shopWindow.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.shopWindow.plugin.ShopWindowConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年1月31日 11:23:09
 */
public class ShopWindowEditorDescriptor implements EditorDescriptor {

	final Provider<ShopWindowEditor> editProvider;

	@Inject
	ShopWindowEditorDescriptor(Provider<ShopWindowEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return ShopWindowConstants.EDITOR_SHOPWINDOW_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		ShopWindowEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("橱窗功能");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
