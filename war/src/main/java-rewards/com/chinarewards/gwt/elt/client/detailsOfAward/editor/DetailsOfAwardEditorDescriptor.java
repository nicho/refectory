/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfAward.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.detailsOfAward.plugin.DetailsOfAwardConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author nicho
 * @since 2011年12月22日 10:02:45
 */
public class DetailsOfAwardEditorDescriptor implements EditorDescriptor {

	final Provider<DetailsOfAwardEditor> editProvider;

	@Inject
	DetailsOfAwardEditorDescriptor(Provider<DetailsOfAwardEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DetailsOfAwardEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("颁奖详细");
		e.setModel(instanceId,model);
		return e;
	}

}
