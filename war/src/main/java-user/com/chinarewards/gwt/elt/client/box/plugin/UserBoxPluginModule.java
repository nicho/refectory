/**
 * 
 */
package com.chinarewards.gwt.elt.client.box.plugin;

import com.chinarewards.gwt.elt.client.box.editor.UserBoxEditor;
import com.chinarewards.gwt.elt.client.box.editor.UserBoxEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2012年2月1日 13:38:07
 */
public class UserBoxPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserBoxPluginDescriptor.class).in(Singleton.class);
		bind(UserBoxEditorDescriptor.class).in(Singleton.class);
		bind(UserBoxEditor.class);
	}

}
