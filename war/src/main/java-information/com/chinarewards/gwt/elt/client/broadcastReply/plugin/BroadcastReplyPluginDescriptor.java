/**
 * 
 */
package com.chinarewards.gwt.elt.client.broadcastReply.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.broadcastReply.editor.BroadcastReplyEditorDescriptor;
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
public class BroadcastReplyPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final BroadcastReplyPlugin userPlugin;
	final BroadcastReplyEditorDescriptor broadcastReplyRegisterEditorDescriptor;

	@Inject
	public BroadcastReplyPluginDescriptor(
			final BroadcastReplyEditorDescriptor broadcastReplyRegisterEditorDescriptor) {
		this.broadcastReplyRegisterEditorDescriptor = broadcastReplyRegisterEditorDescriptor;
		userPlugin = new BroadcastReplyPlugin(this);

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
//						return MenuConstants.MENU_ORDER_BROADCASTREPLY_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return BroadcastReplyConstants.MENU_BROADCASTREPLY_SEARCH;
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
//										BroadcastReplyConstants.EDITOR_BROADCASTREPLY_SEARCH,
//										"EDITOR_BROADCASTREPLY_SEARCH_DO_ID", null);
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
//				return BroadcastReplyPluginDescriptor.this;
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
				return broadcastReplyRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return BroadcastReplyPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return BroadcastReplyConstants.PLUGIN_BROADCASTREPLY;
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
