/**
 * 
 */
package com.chinarewards.gwt.elt.client.colleague.editor;

import com.chinarewards.gwt.elt.client.colleague.plugin.ColleagueListConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class ColleagueListEditorDescriptor implements EditorDescriptor {

	final Provider<ColleagueListEditor> editProvider;

	@Inject
	ColleagueListEditorDescriptor(Provider<ColleagueListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return ColleagueListConstants.EDITOR_COLLEAGUELIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		ColleagueListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("同事列表");
		e.setModel(model);
		return e;
	}

}
