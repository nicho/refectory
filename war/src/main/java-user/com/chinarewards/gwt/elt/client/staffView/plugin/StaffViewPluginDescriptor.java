/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffView.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.chinarewards.gwt.elt.client.staffView.editor.StaffViewEditorDescriptor;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class StaffViewPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final StaffViewPlugin userPlugin;
	final StaffViewEditorDescriptor staffViewRegisterEditorDescriptor;

	@Inject
	public StaffViewPluginDescriptor(
			final StaffViewEditorDescriptor staffViewRegisterEditorDescriptor) {
		this.staffViewRegisterEditorDescriptor = staffViewRegisterEditorDescriptor;
		userPlugin = new StaffViewPlugin(this);

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
//						return MenuConstants.MENU_ORDER_STAFFVIEW_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return StaffViewConstants.MENU_STAFFVIEW_SEARCH;
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
//										StaffViewConstants.EDITOR_STAFFVIEW_SEARCH,
//										"EDITOR_STAFFVIEW_SEARCH_DO_ID", null);
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
//				return StaffViewPluginDescriptor.this;
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
				return staffViewRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return StaffViewPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return StaffViewConstants.PLUGIN_STAFFVIEW;
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
