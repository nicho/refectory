/**
 * 
 */
package com.chinarewards.gwt.elt.client.staff.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staff.plugin.HrRegisterConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class HrRegisterEditorDescriptor implements EditorDescriptor {

	final Provider<HrRegisterEditor> editProvider;

	@Inject
	HrRegisterEditorDescriptor(Provider<HrRegisterEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return HrRegisterConstants.EDITOR_HRREGISTER_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		HrRegisterEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("注册");
		e.setModel(model);
		return e;
	}

}
