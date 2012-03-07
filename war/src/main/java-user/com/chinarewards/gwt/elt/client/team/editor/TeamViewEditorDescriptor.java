package com.chinarewards.gwt.elt.client.team.editor;

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
public class TeamViewEditorDescriptor implements EditorDescriptor {

	final Provider<TeamViewEditor> editProvider;

	@Inject
	TeamViewEditorDescriptor(Provider<TeamViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderViewConstants.EDITOR_ORDERVIEW_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		TeamViewEditor e = editProvider.get();
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
