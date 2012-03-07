/**
 * 
 */
package com.chinarewards.gwt.elt.client.colleagueParticular.plugin;

import com.chinarewards.gwt.elt.client.colleagueParticular.editor.ColleagueParticularEditor;
import com.chinarewards.gwt.elt.client.colleagueParticular.editor.ColleagueParticularEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年3月1日 09:29:29
 */
public class ColleagueParticularPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ColleagueParticularPluginDescriptor.class).in(Singleton.class);

		bind(ColleagueParticularEditorDescriptor.class).in(Singleton.class);
		bind(ColleagueParticularEditor.class);
	}

}
