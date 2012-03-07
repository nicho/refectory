package com.chinarewards.gwt.elt.client.enterprise.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.enterprise.editor.PeriodEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

public class PeriodPluginDescriptor implements PluginDescriptor {
	final static Set<Extension> extensions = new HashSet<Extension>();
	final static String PLUGIN_ID = EnterpriseConstants.PLUGIN_ENTERPRISE;
	final PeriodPlugin plugin;
	final PeriodEditorDescriptor descriptor;

	@Inject
	public PeriodPluginDescriptor(final PeriodEditorDescriptor editorDesc) {
		this.descriptor = editorDesc;
		plugin = new PeriodPlugin(this);
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
										EnterpriseConstants.EDITOR_PERIOD_EDIT,
										"PeriodInstanceID", null);
					}

					public Image getIcon() {
						return null;
					}

					public String getMenuId() {
						return EnterpriseConstants.MENU_PERIOD_EDIT;
					}

					public int getOrder() {
						return MenuConstants.MENU_ORDER_PERIOD_EDIT;
					}

					public String getParentMenuId() {
						return null;
					}

					public String getTitle() {
						return "财年周期设置";
					}

				};
			}

			public PluginDescriptor getPluginDescriptor() {
				return PeriodPluginDescriptor.this;
			}

		});

		extensions.add(new Extension() {
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			public Object getInstance() {
				return descriptor;
			}

			public PluginDescriptor getPluginDescriptor() {
				return PeriodPluginDescriptor.this;
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
