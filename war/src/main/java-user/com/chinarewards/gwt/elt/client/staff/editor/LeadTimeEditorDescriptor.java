package com.chinarewards.gwt.elt.client.staff.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.staff.plugin.LeadTimeConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class LeadTimeEditorDescriptor implements EditorDescriptor {

	Provider<LeadTimeEditor> editorProvider;

	@Inject
	LeadTimeEditorDescriptor(Provider<LeadTimeEditor> editorProvider) {
		// as new Instance of LeadTimeEditor should be provided
		// so use provider instead
		this.editorProvider = editorProvider;
	}

	public Editor createEditor(String instanceId, Object model) {
		LeadTimeEditor e = editorProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("颁奖提前通知时间设置");
		e.setModel(model);
		return e;
	}

	public String getEditorId() {
		return LeadTimeConstants.EDITOR_LEADTIME_SEARCH;
	}

}
