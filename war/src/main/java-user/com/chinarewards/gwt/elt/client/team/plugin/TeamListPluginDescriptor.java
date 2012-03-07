/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.plugin;

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
import com.chinarewards.gwt.elt.client.team.editor.TeamListEditorDescriptor;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since
 */
public class TeamListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final TeamListPlugin teamListPlugin;
	final TeamListEditorDescriptor teamListEditorDescriptor;

	@Inject
	public TeamListPluginDescriptor(final TeamListEditorDescriptor teamListEditorDescriptor) {
		this.teamListEditorDescriptor = teamListEditorDescriptor;
		teamListPlugin = new TeamListPlugin(this);

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
						return MenuConstants.MENU_ORDER_TEAMLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return TeamConstants.MENU_TEAMLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "小组列表";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										TeamConstants.EDITOR_TEAMLIST_SEARCH,
										"EDITOR_TEAMLIST_SEARCH_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return TeamListPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return teamListEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return TeamListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return TeamConstants.PLUGIN_TEAM;
	}

	@Override
	public Plugin getInstance() {
		return teamListPlugin;
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
