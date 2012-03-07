/**
 * 
 */
package com.chinarewards.gwt.elt.client.nominate.plugin;

import com.chinarewards.gwt.elt.client.nominate.editor.NominateEditor;
import com.chinarewards.gwt.elt.client.nominate.editor.NominateEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class NominatePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(NominatePluginDescriptor.class).in(Singleton.class);

		bind(NominateEditorDescriptor.class).in(Singleton.class);
		bind(NominateEditor.class);
	}

}
