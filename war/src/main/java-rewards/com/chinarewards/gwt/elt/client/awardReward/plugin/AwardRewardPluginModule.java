/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardReward.plugin;

import com.chinarewards.gwt.elt.client.awardReward.editor.AwardRewardEditor;
import com.chinarewards.gwt.elt.client.awardReward.editor.AwardRewardEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class AwardRewardPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AwardRewardPluginDescriptor.class).in(Singleton.class);

		bind(AwardRewardEditorDescriptor.class).in(Singleton.class);
		bind(AwardRewardEditor.class);
	}

}
