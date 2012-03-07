/**
 * 
 */
package com.chinarewards.gwt.elt.client.box.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.box.editor.UserBoxEditorDescriptor;
import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:37:23
 */
public class UserBoxPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final UserBoxPlugin UserBoxPlugin;
	final UserBoxEditorDescriptor orderBoxEditorDescriptor;

	@Inject
	public UserBoxPluginDescriptor(
			final UserBoxEditorDescriptor orderBoxEditorDescriptor) {
		this.orderBoxEditorDescriptor = orderBoxEditorDescriptor;
		UserBoxPlugin = new UserBoxPlugin(this);

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
						return MenuConstants.MENU_ORDER_USERBOX_EDIT;
					}

					@Override
					public String getMenuId() {
						return UserBoxConstants.MENU_USERBOX_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "收件箱";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										UserBoxConstants.EDITOR_USERBOX_SEARCH,
										"EDITOR_USERBOX_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserBoxPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return orderBoxEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserBoxPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return UserBoxConstants.PLUGIN_USERBOX;
	}

	@Override
	public Plugin getInstance() {
		return UserBoxPlugin;
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
