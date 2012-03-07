/**
 * 
 */
package com.chinarewards.gwt.elt.client.nominate.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.nominate.editor.NominateEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class NominatePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final NominatePlugin nominatePlugin;
	final NominateEditorDescriptor nominateEditorDescriptor;

	@Inject
	public NominatePluginDescriptor(
			final NominateEditorDescriptor nominateEditorDescriptor) {
		this.nominateEditorDescriptor = nominateEditorDescriptor;
		nominatePlugin = new NominatePlugin(this);

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
						return MenuConstants.MENU_ORDER_MONINATE_SEARCH;
					}

					@Override
					public String getMenuId() {
						return NominateConstants.MENU_NOMINATE_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "待提名奖项";
					}

					@Override
					public void execute() {
						RewardsPageClient rpc=new RewardsPageClient();
						rpc.setTitleName("待提名奖项");
						rpc.setPageType(RewardPageType.NOMINATEPAGE);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
										"EDITOR_REWARDSLIST_"+RewardPageType.NOMINATEPAGE,rpc);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return NominatePluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return nominateEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return NominatePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return NominateConstants.PLUGIN_NOMINATE;
	}

	@Override
	public Plugin getInstance() {
		return nominatePlugin;
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
