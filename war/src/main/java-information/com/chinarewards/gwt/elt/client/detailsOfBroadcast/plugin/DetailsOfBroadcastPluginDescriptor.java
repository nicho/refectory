/**
 * 
 */
package com.chinarewards.gwt.elt.client.detailsOfBroadcast.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.editor.DetailsOfBroadcastEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DetailsOfBroadcastPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DetailsOfBroadcastPlugin userPlugin;
	final DetailsOfBroadcastEditorDescriptor detailsOfBroadcastRegisterEditorDescriptor;

	@Inject
	public DetailsOfBroadcastPluginDescriptor(
			final DetailsOfBroadcastEditorDescriptor detailsOfBroadcastRegisterEditorDescriptor) {
		this.detailsOfBroadcastRegisterEditorDescriptor = detailsOfBroadcastRegisterEditorDescriptor;
		userPlugin = new DetailsOfBroadcastPlugin(this);

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
//						return MenuConstants.MENU_ORDER_DETAILSOFBROADCAST_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return DetailsOfBroadcastConstants.MENU_DETAILSOFBROADCAST_SEARCH;
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
//										DetailsOfBroadcastConstants.EDITOR_DETAILSOFBROADCAST_SEARCH,
//										"EDITOR_DETAILSOFBROADCAST_SEARCH_DO_ID", null);
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
//				return DetailsOfBroadcastPluginDescriptor.this;
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
				return detailsOfBroadcastRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DetailsOfBroadcastPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DetailsOfBroadcastConstants.PLUGIN_DETAILSOFBROADCAST;
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
