package com.chinarewards.gwt.elt.client.orderHistory.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */

public class OrderHistoryViewEditorDescriptor implements EditorDescriptor {

	final Provider<OrderHistoryViewEditor> editProvider;

	@Inject
	OrderHistoryViewEditorDescriptor(
			Provider<OrderHistoryViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderHistoryConstants.EDITOR_ORDERHISTORY_VIEW;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderHistoryViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("查看兑换详细");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(instanceId, model);
		return e;
	}

}
