/**
 * 
 */
package com.chinarewards.gwt.elt.client.corpBroadcast.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.corpBroadcast.editor.CorpBroadcastEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class CorpBroadcastPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final CorpBroadcastPlugin userPlugin;
	final CorpBroadcastEditorDescriptor corpBroadcastRegisterEditorDescriptor;

	@Inject
	public CorpBroadcastPluginDescriptor(
			final CorpBroadcastEditorDescriptor corpBroadcastRegisterEditorDescriptor) {
		this.corpBroadcastRegisterEditorDescriptor = corpBroadcastRegisterEditorDescriptor;
		userPlugin = new CorpBroadcastPlugin(this);

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
//						return MenuConstants.MENU_ORDER_CORPBROADCAST_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return CorpBroadcastConstants.MENU_CORPBROADCAST_SEARCH;
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
//										CorpBroadcastConstants.EDITOR_CORPBROADCAST_SEARCH,
//										"EDITOR_CORPBROADCAST_SEARCH_DO_ID", null);
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
//				return CorpBroadcastPluginDescriptor.this;
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
				return corpBroadcastRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CorpBroadcastPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return CorpBroadcastConstants.PLUGIN_CORPBROADCAST;
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
