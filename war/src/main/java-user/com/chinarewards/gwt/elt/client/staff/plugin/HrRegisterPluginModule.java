/**
 * 
 */
package com.chinarewards.gwt.elt.client.staff.plugin;

import com.chinarewards.gwt.elt.client.staff.editor.HrRegisterEditor;
import com.chinarewards.gwt.elt.client.staff.editor.HrRegisterEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author Cream
 * @since 0.0.1
 */
public class HrRegisterPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(HrRegisterPluginDescriptor.class).in(Singleton.class);

		bind(HrRegisterEditorDescriptor.class).in(Singleton.class);
		bind(HrRegisterEditor.class);
	}

}
