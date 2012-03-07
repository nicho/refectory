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
import com.chinarewards.gwt.elt.client.team.editor.TeamAddEditorDescriptor;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:37:23
 */
public class TeamAddPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final TeamAddPlugin teamAddPlugin;
	final TeamAddEditorDescriptor teamAddEditorDescriptor;

	@Inject
	public TeamAddPluginDescriptor(	final TeamAddEditorDescriptor teamAddEditorDescriptor) {
		this.teamAddEditorDescriptor = teamAddEditorDescriptor;
		teamAddPlugin = new TeamAddPlugin(this);

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
						return MenuConstants.MENU_ORDER_TEAMADD_EDIT;
					}

					@Override
					public String getMenuId() {
						return TeamConstants.EDITOR_TEAM_ADD;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "小组增加";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										TeamConstants.EDITOR_TEAM_ADD,
										"EDITOR_TEAM_ADD_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return TeamAddPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return teamAddEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return TeamAddPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return TeamConstants.PLUGIN_TEAM;
	}

	@Override
	public Plugin getInstance() {
		return teamAddPlugin;
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
