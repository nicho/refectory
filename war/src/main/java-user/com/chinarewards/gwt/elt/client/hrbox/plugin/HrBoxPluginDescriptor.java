/**
 * 
 */
package com.chinarewards.gwt.elt.client.hrbox.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.hrbox.editor.HrBoxEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:37:23
 */
public class HrBoxPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final HrBoxPlugin HrBoxPlugin;
	final HrBoxEditorDescriptor hrBoxEditorDescriptor;

	@Inject
	public HrBoxPluginDescriptor(
			final HrBoxEditorDescriptor hrBoxEditorDescriptor) {
		this.hrBoxEditorDescriptor = hrBoxEditorDescriptor;
		HrBoxPlugin = new HrBoxPlugin(this);

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
						return MenuConstants.MENU_ORDER_HRBOX_EDIT;
					}

					@Override
					public String getMenuId() {
						return HrBoxConstants.MENU_HRBOX_SEARCH;
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
										HrBoxConstants.EDITOR_HRBOX_SEARCH,
										"EDITOR_HRBOX_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return HrBoxPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return hrBoxEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return HrBoxPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return HrBoxConstants.PLUGIN_HRBOX;
	}

	@Override
	public Plugin getInstance() {
		return HrBoxPlugin;
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
