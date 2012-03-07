package com.chinarewards.gwt.elt.client.staffIntegral.plugin;

import com.chinarewards.gwt.elt.client.staffIntegral.editor.StaffIntegralEditor;
import com.chinarewards.gwt.elt.client.staffIntegral.editor.StaffIntegralEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 */
public class StaffIntegralPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(StaffIntegralPluginDescriptor.class).in(Singleton.class);

		bind(StaffIntegralEditorDescriptor.class).in(Singleton.class);
		bind(StaffIntegralEditor.class);
	}

}
