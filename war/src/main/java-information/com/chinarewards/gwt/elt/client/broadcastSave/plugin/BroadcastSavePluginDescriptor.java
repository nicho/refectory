/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastSave.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.broadcastSave.editor.BroadcastSaveEditorDescriptor;
import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class BroadcastSavePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final BroadcastSavePlugin userPlugin;
	final BroadcastSaveEditorDescriptor broadcastSaveRegisterEditorDescriptor;

	@Inject
	public BroadcastSavePluginDescriptor(
			final BroadcastSaveEditorDescriptor broadcastSaveRegisterEditorDescriptor) {
		this.broadcastSaveRegisterEditorDescriptor = broadcastSaveRegisterEditorDescriptor;
		userPlugin = new BroadcastSavePlugin(this);

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
//						return MenuConstants.MENU_ORDER_BROADCASTSAVE_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return BroadcastSaveConstants.MENU_BROADCASTSAVE_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "员工列表";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										BroadcastSaveConstants.EDITOR_BROADCASTSAVE_SEARCH,
//										"EDITOR_BROADCASTSAVE_SEARCH_DO_ID", null);
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
//				return BroadcastSavePluginDescriptor.this;
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
				return broadcastSaveRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return BroadcastSavePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return BroadcastSaveConstants.PLUGIN_BROADCASTSAVE;
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
