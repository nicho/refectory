/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffAdd.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffAdd.plugin.StaffAddConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class StaffAddEditorDescriptor implements EditorDescriptor {

	final Provider<StaffAddEditor> editProvider;

	@Inject
	StaffAddEditorDescriptor(Provider<StaffAddEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return StaffAddConstants.EDITOR_STAFFADD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		StaffAddEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("添加员工");
		e.setModel(model);
		return e;
	}

}
