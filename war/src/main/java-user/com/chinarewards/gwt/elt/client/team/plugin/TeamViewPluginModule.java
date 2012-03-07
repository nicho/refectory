/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.plugin;

import com.chinarewards.gwt.elt.client.order.editor.OrderViewEditor;
import com.chinarewards.gwt.elt.client.order.editor.OrderViewEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2012年2月1日 13:38:07
 */
public class TeamViewPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TeamViewPluginDescriptor.class).in(Singleton.class);
		bind(OrderViewEditorDescriptor.class).in(Singleton.class);
		bind(OrderViewEditor.class);
		
		
	}

}
