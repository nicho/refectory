/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffInfo.plugin;

import com.chinarewards.gwt.elt.client.staffInfo.editor.StaffInfoEditor;
import com.chinarewards.gwt.elt.client.staffInfo.editor.StaffInfoEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class StaffInfoPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffInfoPluginDescriptor.class).in(Singleton.class);

		bind(StaffInfoEditorDescriptor.class).in(Singleton.class);
		bind(StaffInfoEditor.class);
	}

}
