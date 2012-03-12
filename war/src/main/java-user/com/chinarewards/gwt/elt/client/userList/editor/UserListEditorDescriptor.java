/**
 * 
 */
package com.chinarewards.gwt.elt.client.userList.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staffList.plugin.StaffListConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class UserListEditorDescriptor implements EditorDescriptor {

	final Provider<UserListEditor> editProvider;

	@Inject
	UserListEditorDescriptor(Provider<UserListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return UserListConstants.EDITOR_STAFFLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		UserListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("员工列表");
		e.setModel(model);
		return e;
	}

}
