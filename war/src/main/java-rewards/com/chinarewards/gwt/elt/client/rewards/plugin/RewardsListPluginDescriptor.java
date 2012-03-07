/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.plugin;

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
import com.chinarewards.gwt.elt.client.rewards.editor.RewardsListEditorDescriptor;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since
 */
public class RewardsListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final RewardsListPlugin RewardsListPlugin;
	final RewardsListEditorDescriptor rewardsListEditorDescriptor;

	@Inject
	public RewardsListPluginDescriptor(
			final RewardsListEditorDescriptor rewardsListEditorDescriptor) {
		this.rewardsListEditorDescriptor = rewardsListEditorDescriptor;
		RewardsListPlugin = new RewardsListPlugin(this);

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
						return MenuConstants.MENU_ORDER_REWARDSLIST_SEARCH;
					}

					@Override
					public String getMenuId() {
						return RewardsListConstants.MENU_REWARDSLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "应用奖项列表";
					}

					@Override
					public void execute() {
						RewardsPageClient rpc=new RewardsPageClient();
						rpc.setTitleName("应用奖项列表");
						rpc.setPageType(RewardPageType.APPLYREWARDLIST);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
										"EDITOR_REWARDSLIST_SEARCH_DO_ID", rpc);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RewardsListPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return rewardsListEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RewardsListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return RewardsListConstants.PLUGIN_REWARDSLIST;
	}

	@Override
	public Plugin getInstance() {
		return RewardsListPlugin;
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
