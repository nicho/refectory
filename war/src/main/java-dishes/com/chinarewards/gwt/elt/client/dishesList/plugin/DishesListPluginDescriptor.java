/**
 * 
 */
package com.chinarewards.gwt.elt.client.dishesList.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.dishesList.editor.DishesListEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DishesListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DishesListPlugin userPlugin;
	final DishesListEditorDescriptor dishesListRegisterEditorDescriptor;

	@Inject
	public DishesListPluginDescriptor(
			final DishesListEditorDescriptor dishesListRegisterEditorDescriptor) {
		this.dishesListRegisterEditorDescriptor = dishesListRegisterEditorDescriptor;
		userPlugin = new DishesListPlugin(this);

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
						return MenuConstants.MENU_ORDER_DISHESLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return DishesListConstants.MENU_DISHESLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "员工列表";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DishesListConstants.EDITOR_DISHESLIST_SEARCH,
										"EDITOR_DISHESLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return dishesListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DishesListConstants.PLUGIN_DISHESLIST;
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
