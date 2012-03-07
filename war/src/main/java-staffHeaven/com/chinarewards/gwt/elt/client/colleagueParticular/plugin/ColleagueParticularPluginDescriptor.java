/**
 * 
 */
package com.chinarewards.gwt.elt.client.colleagueParticular.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.colleagueParticular.editor.ColleagueParticularEditorDescriptor;
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
public class ColleagueParticularPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final ColleagueParticularPlugin userPlugin;
	final ColleagueParticularEditorDescriptor colleagueParticularRegisterEditorDescriptor;

	@Inject
	public ColleagueParticularPluginDescriptor(
			final ColleagueParticularEditorDescriptor colleagueParticularRegisterEditorDescriptor) {
		this.colleagueParticularRegisterEditorDescriptor = colleagueParticularRegisterEditorDescriptor;
		userPlugin = new ColleagueParticularPlugin(this);
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
//						return MenuConstants.MENU_ORDER_COLLEAGUEPARTICULAR_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return ColleagueParticularConstants.MENU_COLLEAGUEPARTICULAR_SEARCH;
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
//										ColleagueParticularConstants.EDITOR_COLLEAGUEPARTICULAR_SEARCH,
//										"EDITOR_COLLEAGUEPARTICULAR_SEARCH_DO_ID", null);
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
//				return ColleagueParticularPluginDescriptor.this;
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
				return colleagueParticularRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return ColleagueParticularPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return ColleagueParticularConstants.PLUGIN_COLLEAGUEPARTICULAR;
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
