/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfBroadcast.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.plugin.DetailsOfBroadcastConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:54
 */
public class DetailsOfBroadcastEditorDescriptor implements EditorDescriptor {

	final Provider<DetailsOfBroadcastEditor> editProvider;

	@Inject
	DetailsOfBroadcastEditorDescriptor(Provider<DetailsOfBroadcastEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DetailsOfBroadcastConstants.EDITOR_DETAILSOFBROADCAST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DetailsOfBroadcastEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("广播详细");
		e.setModel(model);
		return e;
	}

}
