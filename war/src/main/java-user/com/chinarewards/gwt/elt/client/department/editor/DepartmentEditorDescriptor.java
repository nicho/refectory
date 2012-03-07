package com.chinarewards.gwt.elt.client.department.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class DepartmentEditorDescriptor implements EditorDescriptor {

	final Provider<DepartmentEditor> editProvider;

	@Inject
	DepartmentEditorDescriptor(Provider<DepartmentEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DepartmentConstants.EDITOR_DEPARTMENT_EDIT;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DepartmentEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("编辑部门");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
