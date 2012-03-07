/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderConfirmation.plugin;

import com.chinarewards.gwt.elt.client.orderConfirmation.editor.OrderConfirmationEditor;
import com.chinarewards.gwt.elt.client.orderConfirmation.editor.OrderConfirmationEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class OrderConfirmationPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderConfirmationPluginDescriptor.class).in(Singleton.class);

		bind(OrderConfirmationEditorDescriptor.class).in(Singleton.class);
		bind(OrderConfirmationEditor.class);
	}

}
