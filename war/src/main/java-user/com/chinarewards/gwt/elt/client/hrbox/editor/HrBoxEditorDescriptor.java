package com.chinarewards.gwt.elt.client.hrbox.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.hrbox.plugin.HrBoxConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author lw
 * @since 2012年2月1日 13:36:10
 */
public class HrBoxEditorDescriptor implements EditorDescriptor {

	final Provider<HrBoxEditor> editProvider;

	@Inject
	HrBoxEditorDescriptor(Provider<HrBoxEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return HrBoxConstants.EDITOR_HRBOX_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		HrBoxEditor e = editProvider.get();
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
