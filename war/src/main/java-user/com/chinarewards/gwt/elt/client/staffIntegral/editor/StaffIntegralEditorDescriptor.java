package com.chinarewards.gwt.elt.client.staffIntegral.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffIntegral.plugin.StaffIntegralConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class StaffIntegralEditorDescriptor implements EditorDescriptor {

	final Provider<StaffIntegralEditor> editProvider;

	@Inject
	StaffIntegralEditorDescriptor(Provider<StaffIntegralEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return StaffIntegralConstants.EDITOR_STAFFINTEGRAL_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		StaffIntegralEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工查看");
		e.setModel(model);
		return e;
	}

}
