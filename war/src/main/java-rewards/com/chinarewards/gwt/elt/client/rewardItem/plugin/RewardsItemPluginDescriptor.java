/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.plugin;

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
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemEditorDescriptor;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2011年12月9日 15:36:15
 */
public class RewardsItemPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final RewardsItemPlugin rewardsItemPlugin;
	final RewardsItemEditorDescriptor rewardsItemEditorDescriptor;

	@Inject
	public RewardsItemPluginDescriptor(
			final RewardsItemEditorDescriptor rewardsItemEditorDescriptor) {
		this.rewardsItemEditorDescriptor = rewardsItemEditorDescriptor;
		rewardsItemPlugin = new RewardsItemPlugin(this);

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
						return MenuConstants.MENU_ORDER_REWARDSITEM_ADD;
					}

					@Override
					public String getMenuId() {
						return RewardsItemConstants.MENU_REWARDSITEM_ADD;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "创建奖项";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsItemConstants.EDITOR_REWARDSITEM_ADD,
										"EDITOR_REWARDSITEM_ADD_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RewardsItemPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return rewardsItemEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return RewardsItemPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return RewardsItemConstants.PLUGIN_REWARDSITEM;
	}

	@Override
	public Plugin getInstance() {
		return rewardsItemPlugin;
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
