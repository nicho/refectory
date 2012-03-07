package com.chinarewards.gwt.elt.client.rewardItem.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemViewStaffEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class RewardsItemViewStaffPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final RewardsItemViewStaffPlugin RewardsItemViewStaffPlugin;
	final RewardsItemViewStaffEditorDescriptor RewardsItemViewStaffEditorDescriptor;

	@Inject
	public RewardsItemViewStaffPluginDescriptor(
			final RewardsItemViewStaffEditorDescriptor RewardsItemViewStaffEditorDescriptor) {
		this.RewardsItemViewStaffEditorDescriptor = RewardsItemViewStaffEditorDescriptor;
		RewardsItemViewStaffPlugin = new RewardsItemViewStaffPlugin(this);

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return RewardsItemViewStaffEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RewardsItemViewStaffPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return RewardsItemConstants.PLUGIN_REWARDSITEM;
	}

	@Override
	public Plugin getInstance() {
		return RewardsItemViewStaffPlugin;
	}

	@Override
	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	@Override
	public Set<Extension> getExtensions() {
		return ext;
	}

}
