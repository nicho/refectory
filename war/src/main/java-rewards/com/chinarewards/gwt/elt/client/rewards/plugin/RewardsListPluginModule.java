/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.plugin;

import com.chinarewards.gwt.elt.client.rewards.editor.RewardsListEditor;
import com.chinarewards.gwt.elt.client.rewards.editor.RewardsListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class RewardsListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RewardsListPluginDescriptor.class).in(Singleton.class);

		bind(RewardsListEditorDescriptor.class).in(Singleton.class);
		bind(RewardsListEditor.class);
	}

}
