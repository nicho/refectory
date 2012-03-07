/**
 * 
 */
package com.chinarewards.gwt.elt.client.budget.editor;

import com.chinarewards.gwt.elt.client.budget.plugin.CreateBudgetConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CreateBudgetEditorDescriptor implements EditorDescriptor {

	final Provider<CreateBudgetEditor> editProvider;

	@Inject
	CreateBudgetEditorDescriptor(Provider<CreateBudgetEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CreateBudgetConstants.EDITOR_CREATE_BUDGET;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CreateBudgetEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("预算列表");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		
		e.setModel(model);
		return e;
	}

}
