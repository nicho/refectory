package com.chinarewards.gwt.elt.client.staffIntegral.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.staffIntegral.editor.StaffIntegralEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class StaffIntegralPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final StaffIntegralPlugin userPlugin;
	final StaffIntegralEditorDescriptor staffIntegralRegisterEditorDescriptor;

	@Inject
	public StaffIntegralPluginDescriptor(
			final StaffIntegralEditorDescriptor staffIntegralRegisterEditorDescriptor) {
		this.staffIntegralRegisterEditorDescriptor = staffIntegralRegisterEditorDescriptor;
		userPlugin = new StaffIntegralPlugin(this);

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return staffIntegralRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return StaffIntegralPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return StaffIntegralConstants.PLUGIN_STAFFINTEGRAL;
	}

	@Override
	public Plugin getInstance() {
		return userPlugin;
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
