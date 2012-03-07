/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffInfo.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffInfo.plugin.StaffInfoConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author lw
 * @since 0.0.1 2012-02-19
 */
public class StaffInfoEditorDescriptor implements EditorDescriptor {

	final Provider<StaffInfoEditor> editProvider;

	@Inject
	StaffInfoEditorDescriptor(Provider<StaffInfoEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return StaffInfoConstants.EDITOR_STAFFINFO_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		StaffInfoEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("个人资料修改");
		e.setModel(model);
		return e;
	}

}
