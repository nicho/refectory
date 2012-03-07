/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderConfirmation.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.orderConfirmation.plugin.OrderConfirmationConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年1月31日 11:23:09
 */
public class OrderConfirmationEditorDescriptor implements EditorDescriptor {

	final Provider<OrderConfirmationEditor> editProvider;

	@Inject
	OrderConfirmationEditorDescriptor(Provider<OrderConfirmationEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderConfirmationConstants.EDITOR_ORDERCONFIRMATION_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderConfirmationEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单确认");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
