/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.gift.editor.GiftListEditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListConstants;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListPlugin;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListPluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since
 */
public class GiftListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final GiftListPlugin GiftListPlugin;
	final GiftListEditorDescriptor giftListEditorDescriptor;

	@Inject
	public GiftListPluginDescriptor(
			final GiftListEditorDescriptor giftListEditorDescriptor) {
		this.giftListEditorDescriptor = giftListEditorDescriptor;
		GiftListPlugin = new GiftListPlugin(this);

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
						return MenuConstants.MENU_ORDER_GIFTLIST_SEARCH;
					}

					@Override
					public String getMenuId() {
						return GiftListConstants.MENU_GIFTLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "礼品列表";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										GiftListConstants.EDITOR_GIFTLIST_SEARCH,
										"EDITOR_REWARDSLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return GiftListPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return giftListEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return GiftListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return GiftListConstants.PLUGIN_GIFTLIST;
	}

	@Override
	public Plugin getInstance() {
		return GiftListPlugin;
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
