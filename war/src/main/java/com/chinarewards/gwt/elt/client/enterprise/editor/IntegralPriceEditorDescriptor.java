package com.chinarewards.gwt.elt.client.enterprise.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.enterprise.plugin.EnterpriseConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class IntegralPriceEditorDescriptor implements EditorDescriptor {

	Provider<IntegralPriceEditor> editorProvider;

	@Inject
	IntegralPriceEditorDescriptor(Provider<IntegralPriceEditor> editorProvider) {
		// as new Instance of IntegralPriceEditor should be provided
		// so use provider instead
		this.editorProvider = editorProvider;
	}

	public Editor createEditor(String instanceId, Object model) {
		IntegralPriceEditor e = editorProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("积分价值设置");
		e.setModel(model);
		return e;
	}

	public String getEditorId() {
		return EnterpriseConstants.EDITOR_INTEGRAL_PRICE_EDIT;
	}

}
