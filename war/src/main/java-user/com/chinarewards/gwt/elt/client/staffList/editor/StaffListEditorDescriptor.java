/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffList.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffList.plugin.StaffListConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class StaffListEditorDescriptor implements EditorDescriptor {

	final Provider<StaffListEditor> editProvider;

	@Inject
	StaffListEditorDescriptor(Provider<StaffListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return StaffListConstants.EDITOR_STAFFLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		StaffListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工列表");
		e.setModel(model);
		return e;
	}

}
