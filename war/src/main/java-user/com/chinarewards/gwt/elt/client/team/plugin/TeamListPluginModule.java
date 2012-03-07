
package com.chinarewards.gwt.elt.client.team.plugin;

import com.chinarewards.gwt.elt.client.team.editor.TeamListEditor;
import com.chinarewards.gwt.elt.client.team.editor.TeamListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author lw
 * @since 2011年12月9日 
 */
public class TeamListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TeamListPluginDescriptor.class).in(Singleton.class);

		bind(TeamListEditorDescriptor.class).in(Singleton.class);
		bind(TeamListEditor.class);
	}

}
