/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderHistory.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class OrderHistoryEditorDescriptor implements EditorDescriptor {

	final Provider<OrderHistoryEditor> editProvider;

	@Inject
	OrderHistoryEditorDescriptor(Provider<OrderHistoryEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderHistoryEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单列表");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
