/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.plugin;

import com.chinarewards.gwt.elt.client.team.editor.TeamAddEditor;
import com.chinarewards.gwt.elt.client.team.editor.TeamAddEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2012年2月1日 13:38:07
 */
public class TeamAddPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TeamAddPluginDescriptor.class).in(Singleton.class);
		bind(TeamAddEditorDescriptor.class).in(Singleton.class);
		bind(TeamAddEditor.class);
		
		
	}

}
