/**
 * 
 */
package com.chinarewards.gwt.elt.client.hrbox.plugin;

import com.chinarewards.gwt.elt.client.hrbox.editor.HrBoxEditor;
import com.chinarewards.gwt.elt.client.hrbox.editor.HrBoxEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2012年2月1日 13:38:07
 */
public class HrBoxPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(HrBoxPluginDescriptor.class).in(Singleton.class);
		bind(HrBoxEditorDescriptor.class).in(Singleton.class);
		bind(HrBoxEditor.class);
	}

}
