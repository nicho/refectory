/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffHeavenIndex.plugin;

import com.chinarewards.gwt.elt.client.staffHeavenIndex.editor.StaffHeavenIndexEditor;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.editor.StaffHeavenIndexEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月22日 17:52:45
 */
public class StaffHeavenIndexPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffHeavenIndexPluginDescriptor.class).in(Singleton.class);

		bind(StaffHeavenIndexEditorDescriptor.class).in(Singleton.class);
		bind(StaffHeavenIndexEditor.class);
	}

}
