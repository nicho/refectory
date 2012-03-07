/**
 * 
 */
package com.chinarewards.gwt.elt.client.message.plugin;

import com.chinarewards.gwt.elt.client.message.editor.MessageListEditor;
import com.chinarewards.gwt.elt.client.message.editor.MessageListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class MessageListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MessageListPluginDescriptor.class).in(Singleton.class);

		bind(MessageListEditorDescriptor.class).in(Singleton.class);
		bind(MessageListEditor.class);
	}

}
