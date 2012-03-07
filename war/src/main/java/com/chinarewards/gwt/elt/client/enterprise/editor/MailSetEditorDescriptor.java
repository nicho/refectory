package com.chinarewards.gwt.elt.client.enterprise.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.enterprise.plugin.EnterpriseConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MailSetEditorDescriptor implements EditorDescriptor {

	Provider<MailSetEditor> editorProvider;

	@Inject
	MailSetEditorDescriptor(Provider<MailSetEditor> editorProvider) {
		// as new Instance of MailSetEditor should be provided
		// so use provider instead
		this.editorProvider = editorProvider;
	}

	public Editor createEditor(String instanceId, Object model) {
		// as new Instance of MailSetEditor should be provided
		// whether the instance is new or not is determined by the binder
		// if Singleton, then provider always return the same MailSetEditor
		MailSetEditor e = editorProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("企业邮箱设置");
		e.setModel(model);
		return e;
	}

	public String getEditorId() {
		return EnterpriseConstants.EDITOR_MAILSET_EDIT;
	}

}
