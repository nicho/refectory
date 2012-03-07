/**
 * 
 */
package com.chinarewards.gwt.elt.client.messageSave.plugin;

import com.chinarewards.gwt.elt.client.messageSave.editor.MessageSaveEditor;
import com.chinarewards.gwt.elt.client.messageSave.editor.MessageSaveEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class MessageSavePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MessageSavePluginDescriptor.class).in(Singleton.class);

		bind(MessageSaveEditorDescriptor.class).in(Singleton.class);
		bind(MessageSaveEditor.class);
	}

}
