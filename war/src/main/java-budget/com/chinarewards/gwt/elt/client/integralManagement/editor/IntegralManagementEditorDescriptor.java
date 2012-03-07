/**
 * 
 */
package com.chinarewards.gwt.elt.client.integralManagement.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.integralManagement.plugin.IntegralManagementConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月9日 10:17:54
 */
public class IntegralManagementEditorDescriptor implements EditorDescriptor {

	final Provider<IntegralManagementEditor> editProvider;

	@Inject
	IntegralManagementEditorDescriptor(Provider<IntegralManagementEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return IntegralManagementConstants.EDITOR_INTEGRALMANAGEMENT_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		IntegralManagementEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("积分管理");
		e.setModel(model);
		return e;
	}

}
