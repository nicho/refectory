/**
 * 
 */
package com.chinarewards.gwt.elt.client.colleague.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.colleague.editor.ColleagueListEditorDescriptor;
import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.ExtensionPoint;
import com.chinarewards.gwt.elt.client.core.Plugin;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月29日 16:14:22
 */
public class ColleagueListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final ColleagueListPlugin userPlugin;
	final ColleagueListEditorDescriptor colleagueListRegisterEditorDescriptor;

	@Inject
	public ColleagueListPluginDescriptor(
			final ColleagueListEditorDescriptor colleagueListRegisterEditorDescriptor) {
		this.colleagueListRegisterEditorDescriptor = colleagueListRegisterEditorDescriptor;
		userPlugin = new ColleagueListPlugin(this);
//
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
//						return MenuConstants.MENU_ORDER_COLLEAGUELIST_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return ColleagueListConstants.MENU_COLLEAGUELIST_SEARCH;
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
//										ColleagueListConstants.EDITOR_COLLEAGUELIST_SEARCH,
//										"EDITOR_COLLEAGUELIST_SEARCH_DO_ID", null);
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
//				return ColleagueListPluginDescriptor.this;
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
				return colleagueListRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return ColleagueListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return ColleagueListConstants.PLUGIN_COLLEAGUELIST;
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
