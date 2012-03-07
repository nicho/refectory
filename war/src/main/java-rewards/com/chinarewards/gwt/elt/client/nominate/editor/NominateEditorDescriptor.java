/**
 * 
 */
package com.chinarewards.gwt.elt.client.nominate.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.nominate.plugin.NominateConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2011年12月9日 15:36:27
 */
public class NominateEditorDescriptor implements EditorDescriptor {

	final Provider<NominateEditor> editProvider;

	@Inject
	NominateEditorDescriptor(Provider<NominateEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return NominateConstants.EDITOR_NOMINATE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		NominateEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("提名");
		e.setModel(instanceId,model);
		return e;
	}

}
