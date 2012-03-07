/**
 * 
 */
package com.chinarewards.gwt.elt.client.gloryBroadcast.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.gloryBroadcast.plugin.GloryBroadcastConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class GloryBroadcastEditorDescriptor implements EditorDescriptor {

	final Provider<GloryBroadcastEditor> editProvider;

	@Inject
	GloryBroadcastEditorDescriptor(Provider<GloryBroadcastEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return GloryBroadcastConstants.EDITOR_GLORYBROADCAST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		GloryBroadcastEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("光荣榜");
		e.setModel(model);
		return e;
	}

}
