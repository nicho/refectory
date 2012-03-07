/**
 * 
 */
package com.chinarewards.gwt.elt.client.password.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.password.editor.PasswordEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author LW
 * @since
 */
public class PasswordPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final PasswordPlugin passwordPlugin;
	final PasswordEditorDescriptor passwordEditorDescriptor;

	@Inject
	public PasswordPluginDescriptor(final PasswordEditorDescriptor passwordEditorDescriptor) {
		this.passwordEditorDescriptor = passwordEditorDescriptor;
		passwordPlugin = new PasswordPlugin(this);

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
						return MenuConstants.MENU_ORDER_PASSWORD_SEARCH;
					}

					@Override
					public String getMenuId() {
						return PasswordConstants.MENU_PASSWORD_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "密码修改";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										PasswordConstants.EDITOR_PASSWORD_SEARCH,
										"EDITOR_PASSWORD_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return PasswordPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return passwordEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return PasswordPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return PasswordConstants.PLUGIN_PASSWORD;
	}

	@Override
	public Plugin getInstance() {
		return passwordPlugin;
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
