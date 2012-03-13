/**
 * 
 */
package com.chinarewards.gwt.elt.client.dictionaryList.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.MenuConstants;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DictionaryListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DictionaryListPlugin userPlugin;
	final DictionaryListEditorDescriptor dictionaryListRegisterEditorDescriptor;

	@Inject
	public DictionaryListPluginDescriptor(
			final DictionaryListEditorDescriptor dictionaryListRegisterEditorDescriptor) {
		this.dictionaryListRegisterEditorDescriptor = dictionaryListRegisterEditorDescriptor;
		userPlugin = new DictionaryListPlugin(this);

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
						return MenuConstants.MENU_ORDER_DICTIONARYLIST_EDIT;
					}

					@Override
					public String getMenuId() {
						return DictionaryListConstants.MENU_DICTIONARYLIST_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "员工列表";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DictionaryListConstants.EDITOR_DICTIONARYLIST_SEARCH,
										"EDITOR_DICTIONARYLIST_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DictionaryListPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return dictionaryListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DictionaryListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DictionaryListConstants.PLUGIN_DICTIONARYLIST;
	}

	@Override
	public Plugin getInstance() {
		return userPlugin;
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
