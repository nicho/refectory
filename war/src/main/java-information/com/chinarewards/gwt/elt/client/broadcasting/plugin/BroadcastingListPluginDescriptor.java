/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcasting.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.broadcasting.editor.BroadcastingListEditorDescriptor;
import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class BroadcastingListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final BroadcastingListPlugin userPlugin;
	final BroadcastingListEditorDescriptor broadcastingListRegisterEditorDescriptor;

	@Inject
	public BroadcastingListPluginDescriptor(
			final BroadcastingListEditorDescriptor broadcastingListRegisterEditorDescriptor) {
		this.broadcastingListRegisterEditorDescriptor = broadcastingListRegisterEditorDescriptor;
		userPlugin = new BroadcastingListPlugin(this);

		/**
		 * Search user menu
		 */
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			@Override
			public Object getInstance() {
				return new MenuItem() {

					@Override
					public int getOrder() {
						return MenuConstants.MENU_ORDER_BROADCASTINGLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return BroadcastingListConstants.MENU_BROADCASTINGLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "广播列表";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										BroadcastingListConstants.EDITOR_BROADCASTINGLIST_SEARCH,
										"EDITOR_BROADCASTINGLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return BroadcastingListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return broadcastingListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return BroadcastingListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return BroadcastingListConstants.PLUGIN_BROADCASTINGLIST;
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
