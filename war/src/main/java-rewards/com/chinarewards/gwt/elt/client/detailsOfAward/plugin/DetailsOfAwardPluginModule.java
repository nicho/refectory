/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfAward.plugin;

import com.chinarewards.gwt.elt.client.detailsOfAward.editor.DetailsOfAwardEditor;
import com.chinarewards.gwt.elt.client.detailsOfAward.editor.DetailsOfAwardEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class DetailsOfAwardPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DetailsOfAwardPluginDescriptor.class).in(Singleton.class);

		bind(DetailsOfAwardEditorDescriptor.class).in(Singleton.class);
		bind(DetailsOfAwardEditor.class);
	}

}
