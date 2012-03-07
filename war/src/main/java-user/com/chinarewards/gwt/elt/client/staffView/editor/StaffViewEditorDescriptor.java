/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffView.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffView.plugin.StaffViewConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class StaffViewEditorDescriptor implements EditorDescriptor {

	final Provider<StaffViewEditor> editProvider;

	@Inject
	StaffViewEditorDescriptor(Provider<StaffViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return StaffViewConstants.EDITOR_STAFFVIEW_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		StaffViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工查看");
		e.setModel(model);
		return e;
	}

}
