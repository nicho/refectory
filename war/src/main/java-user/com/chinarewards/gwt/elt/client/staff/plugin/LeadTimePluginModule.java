/**
 * 
 */
package com.chinarewards.gwt.elt.client.staff.plugin;

import com.chinarewards.gwt.elt.client.staff.editor.LeadTimeEditor;
import com.chinarewards.gwt.elt.client.staff.editor.LeadTimeEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author Cream
 * @since 0.0.1
 */
public class LeadTimePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LeadTimePluginDescriptor.class).in(Singleton.class);

		bind(LeadTimeEditorDescriptor.class).in(Singleton.class);
		bind(LeadTimeEditor.class);
	}

}
