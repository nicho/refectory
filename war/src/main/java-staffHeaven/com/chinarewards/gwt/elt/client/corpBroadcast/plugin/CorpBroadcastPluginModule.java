/**
 * 
 */
package com.chinarewards.gwt.elt.client.corpBroadcast.plugin;

import com.chinarewards.gwt.elt.client.corpBroadcast.editor.CorpBroadcastEditor;
import com.chinarewards.gwt.elt.client.corpBroadcast.editor.CorpBroadcastEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月22日 17:52:45
 */
public class CorpBroadcastPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CorpBroadcastPluginDescriptor.class).in(Singleton.class);

		bind(CorpBroadcastEditorDescriptor.class).in(Singleton.class);
		bind(CorpBroadcastEditor.class);
	}

}
