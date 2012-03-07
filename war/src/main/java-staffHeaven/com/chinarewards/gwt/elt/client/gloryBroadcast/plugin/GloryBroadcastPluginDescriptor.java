/**
 * 
 */
package com.chinarewards.gwt.elt.client.gloryBroadcast.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.gloryBroadcast.editor.GloryBroadcastEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class GloryBroadcastPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final GloryBroadcastPlugin userPlugin;
	final GloryBroadcastEditorDescriptor gloryBroadcastRegisterEditorDescriptor;

	@Inject
	public GloryBroadcastPluginDescriptor(
			final GloryBroadcastEditorDescriptor gloryBroadcastRegisterEditorDescriptor) {
		this.gloryBroadcastRegisterEditorDescriptor = gloryBroadcastRegisterEditorDescriptor;
		userPlugin = new GloryBroadcastPlugin(this);

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
//						return MenuConstants.MENU_ORDER_GLORYBROADCAST_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return GloryBroadcastConstants.MENU_GLORYBROADCAST_SEARCH;
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
//										GloryBroadcastConstants.EDITOR_GLORYBROADCAST_SEARCH,
//										"EDITOR_GLORYBROADCAST_SEARCH_DO_ID", null);
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
//				return GloryBroadcastPluginDescriptor.this;
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
				return gloryBroadcastRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return GloryBroadcastPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return GloryBroadcastConstants.PLUGIN_GLORYBROADCAST;
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
