
package com.chinarewards.gwt.elt.client.orderHistory.plugin;

import com.chinarewards.gwt.elt.client.orderHistory.editor.OrderHistoryEditor;
import com.chinarewards.gwt.elt.client.orderHistory.editor.OrderHistoryEditorDescriptor;
import com.chinarewards.gwt.elt.client.orderHistory.editor.OrderHistoryViewEditor;
import com.chinarewards.gwt.elt.client.orderHistory.editor.OrderHistoryViewEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2011年12月9日 
 */
public class OrderHistoryPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(OrderHistoryPluginDescriptor.class).in(Singleton.class);

		bind(OrderHistoryEditorDescriptor.class).in(Singleton.class);
		bind(OrderHistoryEditor.class);
		
		bind(OrderHistoryViewPluginDescriptor.class).in(Singleton.class);

		bind(OrderHistoryViewEditorDescriptor.class).in(Singleton.class);
		bind(OrderHistoryViewEditor.class);
	}

}
