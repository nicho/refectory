/**
 * 
 */
package com.chinarewards.gwt.elt.client.colleague.plugin;

import com.chinarewards.gwt.elt.client.colleague.editor.ColleagueListEditor;
import com.chinarewards.gwt.elt.client.colleague.editor.ColleagueListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NICHO
 * @since 2012年2月14日 10:32:00
 */
public class ColleagueListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ColleagueListPluginDescriptor.class).in(Singleton.class);

		bind(ColleagueListEditorDescriptor.class).in(Singleton.class);
		bind(ColleagueListEditor.class);
	}

}
