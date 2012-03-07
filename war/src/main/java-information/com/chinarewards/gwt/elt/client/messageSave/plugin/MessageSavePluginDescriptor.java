/**
 * 
 */
package com.chinarewards.gwt.elt.client.messageSave.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.messageSave.editor.MessageSaveEditorDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class MessageSavePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final MessageSavePlugin userPlugin;
	final MessageSaveEditorDescriptor messageSaveRegisterEditorDescriptor;

	@Inject
	public MessageSavePluginDescriptor(
			final MessageSaveEditorDescriptor messageSaveRegisterEditorDescriptor) {
		this.messageSaveRegisterEditorDescriptor = messageSaveRegisterEditorDescriptor;
		userPlugin = new MessageSavePlugin(this);

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
//						return MenuConstants.MENU_ORDER_MESSAGESAVE_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return MessageSaveConstants.MENU_MESSAGESAVE_SEARCH;
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
//										MessageSaveConstants.EDITOR_MESSAGESAVE_SEARCH,
//										"EDITOR_MESSAGESAVE_SEARCH_DO_ID", null);
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
//				return MessageSavePluginDescriptor.this;
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
				return messageSaveRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return MessageSavePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return MessageSaveConstants.PLUGIN_MESSAGESAVE;
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
