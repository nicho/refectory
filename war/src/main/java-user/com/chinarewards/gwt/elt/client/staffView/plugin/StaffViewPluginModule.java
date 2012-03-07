/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffView.plugin;

import com.chinarewards.gwt.elt.client.staffView.editor.StaffViewEditor;
import com.chinarewards.gwt.elt.client.staffView.editor.StaffViewEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class StaffViewPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffViewPluginDescriptor.class).in(Singleton.class);

		bind(StaffViewEditorDescriptor.class).in(Singleton.class);
		bind(StaffViewEditor.class);
	}

}
