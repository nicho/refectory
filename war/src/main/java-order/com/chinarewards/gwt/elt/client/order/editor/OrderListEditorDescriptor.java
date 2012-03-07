/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.order.plugin.OrderListConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
<<<<<<< HEAD
 * @author nicho
 * @since 2012年1月9日 17:25:09
=======
 * @author yanrui
>>>>>>> refs/remotes/origin/master
 */
public class OrderListEditorDescriptor implements EditorDescriptor {

	final Provider<OrderListEditor> editProvider;

	@Inject
	OrderListEditorDescriptor(Provider<OrderListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderListConstants.EDITOR_ORDERLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderListEditor e = editProvider.get();
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
