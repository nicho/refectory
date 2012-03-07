/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastReply.editor;

import com.chinarewards.gwt.elt.client.broadcastReply.plugin.BroadcastReplyConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:54
 */
public class BroadcastReplyEditorDescriptor implements EditorDescriptor {

	final Provider<BroadcastReplyEditor> editProvider;

	@Inject
	BroadcastReplyEditorDescriptor(Provider<BroadcastReplyEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return BroadcastReplyConstants.EDITOR_BROADCASTREPLY_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		BroadcastReplyEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("添加回复");
		e.setModel(model);
		return e;
	}

}
