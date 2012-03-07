/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcasting.editor;

import com.chinarewards.gwt.elt.client.broadcasting.plugin.BroadcastingListConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class BroadcastingListEditorDescriptor implements EditorDescriptor {

	final Provider<BroadcastingListEditor> editProvider;

	@Inject
	BroadcastingListEditorDescriptor(Provider<BroadcastingListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return BroadcastingListConstants.EDITOR_BROADCASTINGLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		BroadcastingListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("广播列表");
		e.setModel(model);
		return e;
	}

}
