package com.chinarewards.gwt.elt.client.order.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.order.plugin.OrderViewConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author lw
 * @since 2012年2月1日 13:36:10
 */
public class OrderBoxEditorDescriptor implements EditorDescriptor {

	final Provider<OrderBoxEditor> editProvider;

	@Inject
	OrderBoxEditorDescriptor(Provider<OrderBoxEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderViewConstants.EDITOR_ORDERBOX_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderBoxEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("收件箱");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
