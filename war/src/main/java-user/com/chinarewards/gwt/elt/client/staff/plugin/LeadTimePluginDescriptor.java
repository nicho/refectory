package com.chinarewards.gwt.elt.client.staff.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.staff.editor.LeadTimeEditorDescriptor;
import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

public class LeadTimePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> extensions = new HashSet<Extension>();

	final static String PLUGIN_ID = LeadTimeConstants.PLUGIN_LEADTIME;

	final LeadTimePlugin plugin;

	final LeadTimeEditorDescriptor descriptor;

	@Inject
	public LeadTimePluginDescriptor(
			final LeadTimeEditorDescriptor editorDesc) {
		this.descriptor = editorDesc;
		plugin = new LeadTimePlugin(this);
		extensions.add(new Extension() {
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			public Object getInstance() {
				return new MenuItem() {
					public void execute() {
						StaffVo staffvo = new 	StaffVo();
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										LeadTimeConstants.EDITOR_LEADTIME_SEARCH,
										"LeadTimeInstanceID", staffvo);
					}

					public Image getIcon() {
						return null;
					}

					public String getMenuId() {
						return LeadTimeConstants.MENU_LEADTIME_SEARCH;
					}

					public int getOrder() {
						return MenuConstants.MENU_ORDER_LEADTIME_EDIT;
					}

					public String getParentMenuId() {
						return null;
					}

					public String getTitle() {
						return "接收颁奖通知";
					}

				};
			}

			public PluginDescriptor getPluginDescriptor() {
				return LeadTimePluginDescriptor.this;
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
				return LeadTimePluginDescriptor.this;
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
