/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffHeavenIndex.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.editor.StaffHeavenIndexEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class StaffHeavenIndexPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final StaffHeavenIndexPlugin userPlugin;
	final StaffHeavenIndexEditorDescriptor staffHeavenIndexRegisterEditorDescriptor;

	@Inject
	public StaffHeavenIndexPluginDescriptor(
			final StaffHeavenIndexEditorDescriptor staffHeavenIndexRegisterEditorDescriptor) {
		this.staffHeavenIndexRegisterEditorDescriptor = staffHeavenIndexRegisterEditorDescriptor;
		userPlugin = new StaffHeavenIndexPlugin(this);

//		/**
//		 * Search user menu
//		 */
//		ext.add(new Extension() {
//
//			@Override
//			public String getExtensionPointId() {
//				return PluginConstants.MENU;
//			}
//
//			@Override
//			public Object getInstance() {
//				return new MenuItem() {
//
//					@Override
//					public int getOrder() {
//						return MenuConstants.MENU_ORDER_STAFFHEAVENINDEX_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return StaffHeavenIndexConstants.MENU_STAFFHEAVENINDEX_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "广播列表";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										StaffHeavenIndexConstants.EDITOR_STAFFHEAVENINDEX_SEARCH,
//										"EDITOR_STAFFHEAVENINDEX_SEARCH_DO_ID", null);
//					}
//
//					@Override
//					public Image getIcon() {
//						return null;
//					}
//
//				};
//			}
//
//			@Override
//			public PluginDescriptor getPluginDescriptor() {
//				return StaffHeavenIndexPluginDescriptor.this;
//			}
//
//		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return staffHeavenIndexRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return StaffHeavenIndexPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return StaffHeavenIndexConstants.PLUGIN_STAFFHEAVENINDEX;
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
