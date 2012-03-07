/**
 * 
 */
package com.chinarewards.gwt.elt.client.corpBroadcast.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.corpBroadcast.plugin.CorpBroadcastConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class CorpBroadcastEditorDescriptor implements EditorDescriptor {

	final Provider<CorpBroadcastEditor> editProvider;

	@Inject
	CorpBroadcastEditorDescriptor(Provider<CorpBroadcastEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CorpBroadcastConstants.EDITOR_CORPBROADCAST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CorpBroadcastEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("公司广播");
		e.setModel(model);
		return e;
	}

}
