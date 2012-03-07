/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.plugin;

import com.chinarewards.gwt.elt.client.order.editor.OrderBoxEditor;
import com.chinarewards.gwt.elt.client.order.editor.OrderBoxEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2012年2月1日 13:38:07
 */
public class OrderBoxPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderBoxPluginDescriptor.class).in(Singleton.class);
		bind(OrderBoxEditorDescriptor.class).in(Singleton.class);
		bind(OrderBoxEditor.class);
	}

}
