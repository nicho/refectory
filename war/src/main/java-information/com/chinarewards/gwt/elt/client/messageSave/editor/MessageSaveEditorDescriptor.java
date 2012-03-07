/**
 * 
 */
package com.chinarewards.gwt.elt.client.messageSave.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.messageSave.plugin.MessageSaveConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:54
 */
public class MessageSaveEditorDescriptor implements EditorDescriptor {

	final Provider<MessageSaveEditor> editProvider;

	@Inject
	MessageSaveEditorDescriptor(Provider<MessageSaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return MessageSaveConstants.EDITOR_MESSAGESAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		MessageSaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("添加信息");
		e.setModel(model);
		return e;
	}

}
