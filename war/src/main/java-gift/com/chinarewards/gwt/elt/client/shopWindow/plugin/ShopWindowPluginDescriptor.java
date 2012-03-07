/**
 * 
 */
package com.chinarewards.gwt.elt.client.shopWindow.plugin;

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
import com.chinarewards.gwt.elt.client.shopWindow.editor.ShopWindowEditorDescriptor;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月31日 11:39:18
 */
public class ShopWindowPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final ShopWindowPlugin ShopWindowPlugin;
	final ShopWindowEditorDescriptor shopWindowEditorDescriptor;

	@Inject
	public ShopWindowPluginDescriptor(
			final ShopWindowEditorDescriptor shopWindowEditorDescriptor) {
		this.shopWindowEditorDescriptor = shopWindowEditorDescriptor;
		ShopWindowPlugin = new ShopWindowPlugin(this);

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
						return MenuConstants.MENU_ORDER_SHOPWINDOW_SEARCH;
					}

					@Override
					public String getMenuId() {
						return ShopWindowConstants.MENU_SHOPWINDOW_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "橱窗展示";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										ShopWindowConstants.EDITOR_SHOPWINDOW_SEARCH,
										"EDITOR_SHOPWINDOW_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return ShopWindowPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return shopWindowEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return ShopWindowPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return ShopWindowConstants.PLUGIN_SHOPWINDOW;
	}

	@Override
	public Plugin getInstance() {
		return ShopWindowPlugin;
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
