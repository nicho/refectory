package com.chinarewards.gwt.elt.client.department.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentListConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class DepartmentListEditorDescriptor implements EditorDescriptor {

	final Provider<DepartmentListEditor> editProvider;

	@Inject
	DepartmentListEditorDescriptor(Provider<DepartmentListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DepartmentListConstants.EDITOR_DEPARTMENTLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DepartmentListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("公司组织结构");
		if (model instanceof RewardsPageClient) {
			if (model != null)
				e.setTitle(((RewardsPageClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
