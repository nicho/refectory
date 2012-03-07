
package com.chinarewards.gwt.elt.client.password.plugin;

import com.chinarewards.gwt.elt.client.password.editor.PasswordEditor;
import com.chinarewards.gwt.elt.client.password.editor.PasswordEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2012年2月29日 
 */
public class PasswordPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(PasswordPluginDescriptor.class).in(Singleton.class);

		bind(PasswordEditorDescriptor.class).in(Singleton.class);
		bind(PasswordEditor.class);
		
		
	}

}
