package com.chinarewards.gwt.elt.client.budget.plugin;

import com.chinarewards.gwt.elt.client.budget.editor.CorpBudgetEditor;
import com.chinarewards.gwt.elt.client.budget.editor.CorpBudgetEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 * @since
 */
public class CorpBudgetPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CorpBudgetPluginDescriptor.class).in(Singleton.class);

		bind(CorpBudgetEditorDescriptor.class).in(Singleton.class);		
		bind(CorpBudgetEditor.class);
		
	}

}
