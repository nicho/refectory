package com.chinarewards.gwt.elt.client.rewards.plugin;

import com.chinarewards.gwt.elt.client.rewards.editor.RewardsListStaffEditor;
import com.chinarewards.gwt.elt.client.rewards.editor.RewardsListStaffEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 */
public class RewardsListStaffPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RewardsListStaffPluginDescriptor.class).in(Singleton.class);

		bind(RewardsListStaffEditorDescriptor.class).in(Singleton.class);
		bind(RewardsListStaffEditor.class);
	}

}
