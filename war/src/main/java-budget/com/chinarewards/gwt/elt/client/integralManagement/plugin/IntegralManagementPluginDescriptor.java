/**
 * 
 */
package com.chinarewards.gwt.elt.client.integralManagement.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.integralManagement.editor.IntegralManagementEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月31日 11:39:18
 */
public class IntegralManagementPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final IntegralManagementPlugin IntegralManagementPlugin;
	final IntegralManagementEditorDescriptor integralManagementEditorDescriptor;

	@Inject
	public IntegralManagementPluginDescriptor(
			final IntegralManagementEditorDescriptor integralManagementEditorDescriptor) {
		this.integralManagementEditorDescriptor = integralManagementEditorDescriptor;
		IntegralManagementPlugin = new IntegralManagementPlugin(this);

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
						return MenuConstants.MENU_ORDER_INTEGRALMANAGEMENT_SEARCH;
					}

					@Override
					public String getMenuId() {
						return IntegralManagementConstants.MENU_INTEGRALMANAGEMENT_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "今年财年预算";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										IntegralManagementConstants.EDITOR_INTEGRALMANAGEMENT_SEARCH,
										"EDITOR_INTEGRALMANAGEMENT_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return IntegralManagementPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return integralManagementEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return IntegralManagementPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return IntegralManagementConstants.PLUGIN_INTEGRALMANAGEMENT;
	}

	@Override
	public Plugin getInstance() {
		return IntegralManagementPlugin;
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
