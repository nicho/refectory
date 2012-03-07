/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderSubmit.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.orderSubmit.plugin.OrderSubmitConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月1日 13:36:10
 */
public class OrderSubmitEditorDescriptor implements EditorDescriptor {

	final Provider<OrderSubmitEditor> editProvider;

	@Inject
	OrderSubmitEditorDescriptor(Provider<OrderSubmitEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderSubmitConstants.EDITOR_ORDERSUBMIT_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderSubmitEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单提交");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
