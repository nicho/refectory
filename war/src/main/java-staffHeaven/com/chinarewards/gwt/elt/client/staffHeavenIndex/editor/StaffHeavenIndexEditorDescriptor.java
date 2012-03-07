/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffHeavenIndex.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.plugin.StaffHeavenIndexConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class StaffHeavenIndexEditorDescriptor implements EditorDescriptor {

	final Provider<StaffHeavenIndexEditor> editProvider;

	@Inject
	StaffHeavenIndexEditorDescriptor(Provider<StaffHeavenIndexEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return StaffHeavenIndexConstants.EDITOR_STAFFHEAVENINDEX_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		StaffHeavenIndexEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工天地主页");
		e.setModel(model);
		return e;
	}

}
