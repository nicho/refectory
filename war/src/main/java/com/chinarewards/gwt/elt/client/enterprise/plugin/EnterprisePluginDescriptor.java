package com.chinarewards.gwt.elt.client.enterprise.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.enterprise.editor.EnterpriseEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

public class EnterprisePluginDescriptor implements PluginDescriptor {
	final static Set<Extension> extensions = new HashSet<Extension>();
	final static String PLUGIN_ID = EnterpriseConstants.PLUGIN_ENTERPRISE;
	final EnterprisePlugin plugin;
	final EnterpriseEditorDescriptor descriptor;

	@Inject
	public EnterprisePluginDescriptor(
			final EnterpriseEditorDescriptor editorDesc) {
		this.descriptor = editorDesc;
		plugin = new EnterprisePlugin(this);
		extensions.add(new Extension() {
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			public Object getInstance() {
				return new MenuItem() {
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										EnterpriseConstants.EDITOR_ENTERPRISE_EDIT,
										"EnterpriseInstanceID", null);
					}

					public Image getIcon() {
						return null;
					}

					public String getMenuId() {
						return EnterpriseConstants.MENU_ENTERPRISE_EDIT;
					}

					public int getOrder() {
						return 0;
					}

					public String getParentMenuId() {
						return null;
					}

					public String getTitle() {
						return "公司资料";
					}

				};
			}

			public PluginDescriptor getPluginDescriptor() {
				return EnterprisePluginDescriptor.this;
			}

		});

		extensions.add(new Extension() {

			public String getExtensionPointId() {
				return "core.editor";
			}

			public Object getInstance() {
				return descriptor;
			}

			public PluginDescriptor getPluginDescriptor() {
				return EnterprisePluginDescriptor.this;
			}

		});

	}

	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	public Set<Extension> getExtensions() {
		return extensions;
	}

	public Plugin getInstance() {
		return plugin;
	}

	public String getPluginId() {
		return PLUGIN_ID;
	}

}
