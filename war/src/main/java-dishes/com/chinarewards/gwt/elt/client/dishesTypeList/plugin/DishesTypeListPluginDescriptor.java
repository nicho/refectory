/**
 * 
 */
package com.chinarewards.gwt.elt.client.dishesTypeList.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.dishesTypeList.editor.DishesTypeListEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DishesTypeListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DishesTypeListPlugin userPlugin;
	final DishesTypeListEditorDescriptor dishesTypeListRegisterEditorDescriptor;

	@Inject
	public DishesTypeListPluginDescriptor(
			final DishesTypeListEditorDescriptor dishesTypeListRegisterEditorDescriptor) {
		this.dishesTypeListRegisterEditorDescriptor = dishesTypeListRegisterEditorDescriptor;
		userPlugin = new DishesTypeListPlugin(this);

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
						return MenuConstants.MENU_ORDER_DISHESTYPELIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return DishesTypeListConstants.MENU_DISHESTYPELIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "菜单类型";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DishesTypeListConstants.EDITOR_DISHESTYPELIST_SEARCH,
										"EDITOR_DISHESTYPELIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesTypeListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return dishesTypeListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesTypeListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DishesTypeListConstants.PLUGIN_DISHESTYPELIST;
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
