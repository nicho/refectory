package com.chinarewards.gwt.elt.client.order.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.order.plugin.OrderViewConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
<<<<<<< HEAD
 * @author yanrui
=======
 * @author lw
 * @since 2012年2月1日 13:36:10
>>>>>>> refs/remotes/origin/master
 */
public class OrderViewEditorDescriptor implements EditorDescriptor {

	final Provider<OrderViewEditor> editProvider;

	@Inject
	OrderViewEditorDescriptor(Provider<OrderViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderViewConstants.EDITOR_ORDERVIEW_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单详细");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
