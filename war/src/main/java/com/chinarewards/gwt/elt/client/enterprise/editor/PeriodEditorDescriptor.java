package com.chinarewards.gwt.elt.client.enterprise.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.enterprise.plugin.EnterpriseConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PeriodEditorDescriptor implements EditorDescriptor {

	Provider<PeriodEditor> editorProvider;

	@Inject
	PeriodEditorDescriptor(Provider<PeriodEditor> editorProvider) {
		// as new Instance of PeriodEditor should be provided
		// so use provider instead
		this.editorProvider = editorProvider;
	}

	public Editor createEditor(String instanceId, Object model) {
		PeriodEditor e = editorProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("财年周期设置");
		e.setModel(model);
		return e;
	}

	public String getEditorId() {
		return EnterpriseConstants.EDITOR_PERIOD_EDIT;
	}

}
