/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastSave.editor;

import com.chinarewards.gwt.elt.client.broadcastSave.plugin.BroadcastSaveConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:54
 */
public class BroadcastSaveEditorDescriptor implements EditorDescriptor {

	final Provider<BroadcastSaveEditor> editProvider;

	@Inject
	BroadcastSaveEditorDescriptor(Provider<BroadcastSaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return BroadcastSaveConstants.EDITOR_BROADCASTSAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		BroadcastSaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("添加广播");
		e.setModel(model);
		return e;
	}

}
