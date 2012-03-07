/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderSubmit.plugin;

import com.chinarewards.gwt.elt.client.orderSubmit.editor.OrderSubmitEditor;
import com.chinarewards.gwt.elt.client.orderSubmit.editor.OrderSubmitEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2012年2月1日 13:38:07
 */
public class OrderSubmitPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderSubmitPluginDescriptor.class).in(Singleton.class);

		bind(OrderSubmitEditorDescriptor.class).in(Singleton.class);
		bind(OrderSubmitEditor.class);
	}

}
