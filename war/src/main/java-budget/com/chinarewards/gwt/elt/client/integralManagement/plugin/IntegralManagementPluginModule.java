/**
 * 
 */
package com.chinarewards.gwt.elt.client.integralManagement.plugin;

import com.chinarewards.gwt.elt.client.integralManagement.editor.IntegralManagementEditor;
import com.chinarewards.gwt.elt.client.integralManagement.editor.IntegralManagementEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class IntegralManagementPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(IntegralManagementPluginDescriptor.class).in(Singleton.class);

		bind(IntegralManagementEditorDescriptor.class).in(Singleton.class);
		bind(IntegralManagementEditor.class);
	}

}
