/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcasting.plugin;

import com.chinarewards.gwt.elt.client.broadcasting.editor.BroadcastingListEditor;
import com.chinarewards.gwt.elt.client.broadcasting.editor.BroadcastingListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class BroadcastingListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BroadcastingListPluginDescriptor.class).in(Singleton.class);

		bind(BroadcastingListEditorDescriptor.class).in(Singleton.class);
		bind(BroadcastingListEditor.class);
	}

}
