package com.chinarewards.gwt.elt.client.department.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentLeaderConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class DepartmentLeaderEditorDescriptor implements EditorDescriptor {

	final Provider<DepartmentLeaderEditor> editProvider;

	@Inject
	DepartmentLeaderEditorDescriptor(Provider<DepartmentLeaderEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DepartmentLeaderConstants.EDITOR_DEPARTMENTLEADER_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DepartmentLeaderEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("部门组织结构");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
