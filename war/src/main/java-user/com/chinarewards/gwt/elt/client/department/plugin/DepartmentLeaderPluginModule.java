package com.chinarewards.gwt.elt.client.department.plugin;

import com.chinarewards.gwt.elt.client.department.editor.DepartmentLeaderEditor;
import com.chinarewards.gwt.elt.client.department.editor.DepartmentLeaderEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 */
public class DepartmentLeaderPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DepartmentLeaderPluginDescriptor.class).in(Singleton.class);

		bind(DepartmentLeaderEditorDescriptor.class).in(Singleton.class);
		bind(DepartmentLeaderEditor.class);
	}

}
