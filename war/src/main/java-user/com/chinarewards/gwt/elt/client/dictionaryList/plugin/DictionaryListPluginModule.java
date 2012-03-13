/**
 * 
 */
package com.chinarewards.gwt.elt.client.dictionaryList.plugin;

import com.chinarewards.gwt.elt.client.dictionaryList.editor.DictionaryListEditor;
import com.chinarewards.gwt.elt.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DictionaryListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DictionaryListPluginDescriptor.class).in(Singleton.class);

		bind(DictionaryListEditorDescriptor.class).in(Singleton.class);
		bind(DictionaryListEditor.class);
	}

}
