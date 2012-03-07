/**
 * 
 */
package com.chinarewards.gwt.elt.client.colleagueParticular.editor;

import com.chinarewards.gwt.elt.client.colleagueParticular.plugin.ColleagueParticularConstants;
import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class ColleagueParticularEditorDescriptor implements EditorDescriptor {

	final Provider<ColleagueParticularEditor> editProvider;

	@Inject
	ColleagueParticularEditorDescriptor(Provider<ColleagueParticularEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return ColleagueParticularConstants.EDITOR_COLLEAGUEPARTICULAR_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		ColleagueParticularEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("同事详细");
		e.setModel(model);
		return e;
	}

}
