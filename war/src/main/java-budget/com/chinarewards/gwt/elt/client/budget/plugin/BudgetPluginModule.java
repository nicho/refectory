package com.chinarewards.gwt.elt.client.budget.plugin;

import com.chinarewards.gwt.elt.client.budget.editor.CreateBudgetEditor;
import com.chinarewards.gwt.elt.client.budget.editor.CreateBudgetEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * 
 * @author lw
 * @since 2010-12-16
 */
public class BudgetPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BudgetPluginDescriptor.class).in(Singleton.class);

		
		// create
		bind(CreateBudgetEditorDescriptor.class).in(Singleton.class);
		bind(CreateBudgetEditor.class);

		
	}

}
