package com.chinarewards.gwt.elt.client.budget.editor;

import com.chinarewards.gwt.elt.client.budget.plugin.CorpBudgetConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 * @since 2012年1月9日 17:25:09
 */
public class CorpBudgetEditorDescriptor implements EditorDescriptor {

	final Provider<CorpBudgetEditor> editProvider;

	@Inject
	CorpBudgetEditorDescriptor(Provider<CorpBudgetEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CorpBudgetConstants.EDITOR_CORPBUDGET_EDIT;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CorpBudgetEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("整体预算");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
