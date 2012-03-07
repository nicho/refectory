/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfGift.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.detailsOfGift.editor.DetailsOfGiftEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 13:37:23
 */
public class DetailsOfGiftPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DetailsOfGiftPlugin DetailsOfGiftPlugin;
	final DetailsOfGiftEditorDescriptor detailsOfGiftEditorDescriptor;

	@Inject
	public DetailsOfGiftPluginDescriptor(
			final DetailsOfGiftEditorDescriptor detailsOfGiftEditorDescriptor) {
		this.detailsOfGiftEditorDescriptor = detailsOfGiftEditorDescriptor;
		DetailsOfGiftPlugin = new DetailsOfGiftPlugin(this);

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
						return MenuConstants.MENU_ORDER_DETAILSOFGIFT_SEARCH;
					}

					@Override
					public String getMenuId() {
						return DetailsOfGiftConstants.MENU_DETAILSOFGIFT_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "礼品详细";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DetailsOfGiftConstants.EDITOR_DETAILSOFGIFT_SEARCH,
										"EDITOR_DETAILSOFGIFT_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DetailsOfGiftPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return detailsOfGiftEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DetailsOfGiftPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DetailsOfGiftConstants.PLUGIN_DETAILSOFGIFT;
	}

	@Override
	public Plugin getInstance() {
		return DetailsOfGiftPlugin;
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
