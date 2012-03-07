/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastReply.plugin;

import com.chinarewards.gwt.elt.client.broadcastReply.editor.BroadcastReplyEditor;
import com.chinarewards.gwt.elt.client.broadcastReply.editor.BroadcastReplyEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class BroadcastReplyPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BroadcastReplyPluginDescriptor.class).in(Singleton.class);

		bind(BroadcastReplyEditorDescriptor.class).in(Singleton.class);
		bind(BroadcastReplyEditor.class);
	}

}
