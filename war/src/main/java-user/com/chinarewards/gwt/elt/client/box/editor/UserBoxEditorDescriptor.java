package com.chinarewards.gwt.elt.client.box.editor;

import com.chinarewards.gwt.elt.client.box.plugin.UserBoxConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author lw
 * @since 2012年2月1日 13:36:10
 */
public class UserBoxEditorDescriptor implements EditorDescriptor {

	final Provider<UserBoxEditor> editProvider;

	@Inject
	UserBoxEditorDescriptor(Provider<UserBoxEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return UserBoxConstants.EDITOR_USERBOX_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		UserBoxEditor e = editProvider.get();
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
