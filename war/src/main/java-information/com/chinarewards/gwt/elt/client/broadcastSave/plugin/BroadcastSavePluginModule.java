/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastSave.plugin;

import com.chinarewards.gwt.elt.client.broadcastSave.editor.BroadcastSaveEditor;
import com.chinarewards.gwt.elt.client.broadcastSave.editor.BroadcastSaveEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class BroadcastSavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BroadcastSavePluginDescriptor.class).in(Singleton.class);

		bind(BroadcastSaveEditorDescriptor.class).in(Singleton.class);
		bind(BroadcastSaveEditor.class);
	}

}
