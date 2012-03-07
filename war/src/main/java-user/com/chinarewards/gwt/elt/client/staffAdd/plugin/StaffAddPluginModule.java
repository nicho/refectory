/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffAdd.plugin;

import com.chinarewards.gwt.elt.client.staffAdd.editor.StaffAddEditor;
import com.chinarewards.gwt.elt.client.staffAdd.editor.StaffAddEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class StaffAddPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffAddPluginDescriptor.class).in(Singleton.class);

		bind(StaffAddEditorDescriptor.class).in(Singleton.class);
		bind(StaffAddEditor.class);
	}

}
