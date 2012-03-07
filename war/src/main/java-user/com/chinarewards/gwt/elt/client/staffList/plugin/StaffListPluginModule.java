/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffList.plugin;

import com.chinarewards.gwt.elt.client.staffList.editor.StaffListEditor;
import com.chinarewards.gwt.elt.client.staffList.editor.StaffListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class StaffListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffListPluginDescriptor.class).in(Singleton.class);

		bind(StaffListEditorDescriptor.class).in(Singleton.class);
		bind(StaffListEditor.class);
	}

}
