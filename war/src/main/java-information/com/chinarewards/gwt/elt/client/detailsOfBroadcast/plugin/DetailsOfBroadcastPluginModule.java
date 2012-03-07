/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfBroadcast.plugin;

import com.chinarewards.gwt.elt.client.detailsOfBroadcast.editor.DetailsOfBroadcastEditor;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.editor.DetailsOfBroadcastEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class DetailsOfBroadcastPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DetailsOfBroadcastPluginDescriptor.class).in(Singleton.class);

		bind(DetailsOfBroadcastEditorDescriptor.class).in(Singleton.class);
		bind(DetailsOfBroadcastEditor.class);
	}

}
