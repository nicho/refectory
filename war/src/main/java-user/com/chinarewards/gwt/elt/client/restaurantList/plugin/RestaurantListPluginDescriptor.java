/**
 * 
 */
package com.chinarewards.gwt.elt.client.restaurantList.plugin;

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
import com.chinarewards.gwt.elt.client.restaurantList.editor.RestaurantListEditorDescriptor;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class RestaurantListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final RestaurantListPlugin userPlugin;
	final RestaurantListEditorDescriptor restaurantListRegisterEditorDescriptor;

	@Inject
	public RestaurantListPluginDescriptor(
			final RestaurantListEditorDescriptor restaurantListRegisterEditorDescriptor) {
		this.restaurantListRegisterEditorDescriptor = restaurantListRegisterEditorDescriptor;
		userPlugin = new RestaurantListPlugin(this);

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
						return MenuConstants.MENU_ORDER_RESTAURANTLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return RestaurantListConstants.MENU_RESTAURANTLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "分店管理";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RestaurantListConstants.EDITOR_RESTAURANTLIST_SEARCH,
										"EDITOR_RESTAURANTLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RestaurantListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return restaurantListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RestaurantListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return RestaurantListConstants.PLUGIN_RESTAURANTLIST;
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
