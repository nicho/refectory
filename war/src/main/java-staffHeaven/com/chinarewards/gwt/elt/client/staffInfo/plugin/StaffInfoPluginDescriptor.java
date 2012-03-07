/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffInfo.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.staffInfo.editor.StaffInfoEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class StaffInfoPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final StaffInfoPlugin userPlugin;
	final StaffInfoEditorDescriptor staffViewRegisterEditorDescriptor;

	@Inject
	public StaffInfoPluginDescriptor(
			final StaffInfoEditorDescriptor staffViewRegisterEditorDescriptor) {
		this.staffViewRegisterEditorDescriptor = staffViewRegisterEditorDescriptor;
		userPlugin = new StaffInfoPlugin(this);



		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return staffViewRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return StaffInfoPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return StaffInfoConstants.PLUGIN_STAFFINFO;
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
