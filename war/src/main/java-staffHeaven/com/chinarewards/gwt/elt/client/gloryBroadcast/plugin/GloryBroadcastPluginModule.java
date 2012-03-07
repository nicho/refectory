/**
 * 
 */
package com.chinarewards.gwt.elt.client.gloryBroadcast.plugin;

import com.chinarewards.gwt.elt.client.gloryBroadcast.editor.GloryBroadcastEditor;
import com.chinarewards.gwt.elt.client.gloryBroadcast.editor.GloryBroadcastEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月22日 17:52:45
 */
public class GloryBroadcastPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GloryBroadcastPluginDescriptor.class).in(Singleton.class);

		bind(GloryBroadcastEditorDescriptor.class).in(Singleton.class);
		bind(GloryBroadcastEditor.class);
	}

}
