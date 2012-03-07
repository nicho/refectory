/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardShop.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.awardShop.editor.AwardShopListEditorDescriptor;
import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListPlugin;
import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListPluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since
 */
public class AwardShopListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final AwardShopListPlugin AwardShopListPlugin;
	final AwardShopListEditorDescriptor awardShopListEditorDescriptor;

	@Inject
	public AwardShopListPluginDescriptor(
			final AwardShopListEditorDescriptor awardShopListEditorDescriptor) {
		this.awardShopListEditorDescriptor = awardShopListEditorDescriptor;
		AwardShopListPlugin = new AwardShopListPlugin(this);

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
						return MenuConstants.MENU_ORDER_AWARDSHOPLIST_SEARCH;
					}

					@Override
					public String getMenuId() {
						return AwardShopListConstants.MENU_AWARDSHOPLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "奖品商城";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH,
										"EDITOR_AWARDSHOPLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return AwardShopListPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return awardShopListEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return AwardShopListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return AwardShopListConstants.PLUGIN_AWARDSHOPLIST;
	}

	@Override
	public Plugin getInstance() {
		return AwardShopListPlugin;
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
