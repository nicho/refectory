/**
 * 
 */
package com.chinarewards.gwt.elt.client.message.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.message.plugin.MessageListConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class MessageListEditorDescriptor implements EditorDescriptor {

	final Provider<MessageListEditor> editProvider;

	@Inject
	MessageListEditorDescriptor(Provider<MessageListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return MessageListConstants.EDITOR_MESSAGELIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		MessageListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("消息列表");
		e.setModel(model);
		return e;
	}

}
