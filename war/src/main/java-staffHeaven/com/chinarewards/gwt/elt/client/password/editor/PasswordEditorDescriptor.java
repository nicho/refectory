/**
 * 
 */
package com.chinarewards.gwt.elt.client.password.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.password.plugin.PasswordConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class PasswordEditorDescriptor implements EditorDescriptor {

	final Provider<PasswordEditor> editProvider;

	@Inject
	PasswordEditorDescriptor(Provider<PasswordEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return PasswordConstants.EDITOR_PASSWORD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		PasswordEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("密码修改");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
