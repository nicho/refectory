package com.chinarewards.gwt.elt.client.department.plugin;

import com.chinarewards.gwt.elt.client.department.editor.DepartmentListEditor;
import com.chinarewards.gwt.elt.client.department.editor.DepartmentListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 */
public class DepartmentListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DepartmentListPluginDescriptor.class).in(Singleton.class);

		bind(DepartmentListEditorDescriptor.class).in(Singleton.class);
		bind(DepartmentListEditor.class);
	}

}
